package com.nightboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.exception.CommonException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.po.DictPo;
import com.nightboot.domain.po.DictValPo;
import com.nightboot.domain.req.dict.DictPageDto;
import com.nightboot.domain.req.dict.SaveDictDto;
import com.nightboot.domain.req.dict.UpdateDictDto;
import com.nightboot.domain.req.dictval.DictValPageDto;
import com.nightboot.domain.req.dictval.SaveDictValDto;
import com.nightboot.domain.req.dictval.UpdateDictValDto;
import com.nightboot.domain.res.dict.DictInfoVo;
import com.nightboot.domain.res.dict.DictListVo;
import com.nightboot.domain.res.dictval.DictValInfoDto;
import com.nightboot.domain.res.dictval.DictValListVo;
import com.nightboot.mapper.DictMapper;
import com.nightboot.service.DictService;
import com.nightboot.service.DictValService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictPo> implements DictService {

    @Resource
    private DictValService dictValService;

    @Override
    public List<DictListVo> dictValAll() {
        List<DictPo> list = this.list();
        List<DictListVo> dictListVos = new ArrayList<>(list.size());
        list.forEach(dict->{
            DictListVo vo = new DictListVo();
            BeanUtils.copyProperties(dict,vo);
            dictListVos.add(vo);
        });
        dictListVos.forEach(dict->{
            dict.setChildren(dictValService.dictValAll(dict.getId()));
        });
        return dictListVos;
    }

    @Override
    public Page<DictListVo> findAll(DictPageDto dto) {
        Page<DictListVo> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        List<DictListVo> list = this.baseMapper.findAll(page, dto);
        page.setRecords(list);
        return page;
    }

    @Override
    public Page<DictValListVo> findDictValAll(DictValPageDto dto) {
        DictPo dict = getById(dto.getDictId());
        if(StringUtils.isNull(dict)){
            throw CommonException.fail("字典信息不存在");
        }
        return dictValService.findAll(dto);
    }

    @Override
    public DictInfoVo findOne(String id) {
        DictPo po = getById(id);
        if(StringUtils.isNull(po)){
            throw CommonException.fail("字典信息不存在");
        }
        DictInfoVo vo = new DictInfoVo();
        BeanUtils.copyProperties(po,vo);
        return vo;
    }

    @Override
    public DictValInfoDto findDictValOne(String id) {
        DictValPo dictValPo = dictValService.getById(id);
        if(StringUtils.isNull(dictValPo)){
            throw CommonException.fail("字典类型信息不存在");
        }
        DictValInfoDto vo = new DictValInfoDto();
        BeanUtils.copyProperties(dictValPo,vo);
        return vo;
    }

    @Override
    public void save(SaveDictDto dto) {
        DictPo dict = query().eq("dict_type", dto.getDictType()).one();
        if(StringUtils.isNotNull(dict)){
            throw CommonException.fail("字典类型已存在");
        }
        DictPo newDict = new DictPo();
        BeanUtils.copyProperties(dto,newDict);
        newDict.setId(IdUtils.nextId());
        newDict.setCreateBy(SecurityUtils.getUserId());
        save(newDict);
    }

    @Override
    public void update(UpdateDictDto dto) {
        DictPo dict = query().eq("dict_type", dto.getDictType()).one();
        if(StringUtils.isNull(dict)){
            throw CommonException.fail("字典信息不存在");
        }
        if(!dict.getId().equals(dto.getId())){
            throw CommonException.fail("字典信息不存在");
        }
        BeanUtils.copyProperties(dto,dict);
        dict.setUpdateBy(SecurityUtils.getUserId());
        updateById(dict);
    }

    @Override
    public void saveDictVal(SaveDictValDto dto) {
        DictValPo po = new DictValPo();
        BeanUtils.copyProperties(dto,po);
        po.setId(IdUtils.nextId());
        dictValService.save(po);
    }

    @Override
    public void delDictVal(String id) {
        DictValPo dictValPo = dictValService.getById(id);
        if(StringUtils.isNull(dictValPo)){
            throw CommonException.fail("字典类型信息不存在");
        }
        DictValPo child = dictValService.query().eq("parent_id", dictValPo.getId()).one();
        if(StringUtils.isNotNull(child)){
            throw CommonException.fail("请先删除子项");
        }
        dictValService.removeById(dictValPo);
    }

    @Override
    public void updateDictVal(UpdateDictValDto dto) {
        DictValPo dictValPo = dictValService.getById(dto.getId());
        if(StringUtils.isNull(dictValPo)){
            throw CommonException.fail("字典类型信息不存在");
        }
        BeanUtils.copyProperties(dto,dictValPo);
        dictValService.updateById(dictValPo);
    }
}
