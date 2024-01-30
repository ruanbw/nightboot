package com.nightboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nightboot.domain.po.DictValPo;
import com.nightboot.domain.req.dictval.DictValPageDto;
import com.nightboot.domain.res.dictval.DictValListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictValService extends IService<DictValPo> {

    Page<DictValListVo> findAll(DictValPageDto dto);

    List<DictValListVo> dictValAll(String dictId);
}
