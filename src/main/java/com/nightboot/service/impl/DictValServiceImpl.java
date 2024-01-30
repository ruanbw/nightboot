package com.nightboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.po.DictValPo;
import com.nightboot.domain.req.dictval.DictValPageDto;
import com.nightboot.domain.res.dictval.DictValListVo;
import com.nightboot.mapper.DictValMapper;
import com.nightboot.service.DictValService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DictValServiceImpl extends ServiceImpl<DictValMapper, DictValPo> implements DictValService {
    @Override
    public Page<DictValListVo> findAll(DictValPageDto dto) {
        Page<DictValListVo> page = new Page<>(dto.getPageNum(),dto.getPageSize());
        List<DictValListVo> list = this.baseMapper.findAll(dto);
        List<DictValListVo> vos = dictValListToTree(list);
        page.setRecords(vos);
        return page;
    }

    @Override
    public List<DictValListVo> dictValAll(String dictId) {
        DictValPageDto dto = new DictValPageDto();
        dto.setDictId(dictId);
        List<DictValListVo> list = this.baseMapper.findAll(dto);
        return dictValListToTree(list);
    }

    private List<DictValListVo> dictValListToTree(List<DictValListVo> dictValList) {
        List<DictValListVo> topTree = new ArrayList<>();
        Map<String, List<DictValListVo>> childMap = new HashMap<>();
        Map<String, DictValListVo> parentMap = new HashMap<>();
        for (DictValListVo m : dictValList) {
            parentMap.put(m.getId(), m);
            if (StringUtils.isEmpty(m.getParentId())) {
                topTree.add(m);
                List<DictValListVo> childs = childMap.get(m.getId());
                if (Objects.isNull(childs)) {
                    childs = new ArrayList<>();
                    childMap.put(m.getId(), childs);
                }
                m.setChildren(childs);
            } else {
                // 维护兄弟节点
                List<DictValListVo> brothers = childMap.get(m.getParentId());
                if (Objects.isNull(brothers)) {
                    brothers = new ArrayList<>();
                    childMap.put(m.getParentId(), brothers);
                    m.setChildren(brothers);
                }
                brothers.add(m);

                // 维护自己的子节点
                List<DictValListVo> myChilds = childMap.get(m.getId());
                if (Objects.isNull(myChilds)) {
                    myChilds = new ArrayList<>();
                    childMap.put(m.getId(), myChilds);
                }
                m.setChildren(myChilds);
            }
        }
        return topTree;
    }
}
