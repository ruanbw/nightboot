package com.nightboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nightboot.domain.Result;
import com.nightboot.domain.po.DictPo;
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

import java.util.List;

public interface DictService extends IService<DictPo> {

    List<DictListVo> dictValAll();

    Page<DictListVo> findAll(DictPageDto dto);

    Page<DictValListVo> findDictValAll(DictValPageDto dto);

    DictInfoVo findOne(String id);

    DictValInfoDto findDictValOne(String id);

    void save(SaveDictDto dto);

    void saveDictVal(SaveDictValDto dto);

    void delDictVal(String id);

    void update(UpdateDictDto dto);

    void updateDictVal(UpdateDictValDto dto);


}
