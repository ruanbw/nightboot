package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nightboot.domain.po.DictPo;
import com.nightboot.domain.po.DictValPo;
import com.nightboot.domain.req.dept.DeptPageDto;
import com.nightboot.domain.req.dictval.DictValPageDto;
import com.nightboot.domain.res.dept.DeptListVo;
import com.nightboot.domain.res.dictval.DictValListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictValMapper extends BaseMapper<DictValPo> {

    List<DictValListVo> findAll(@Param("q") DictValPageDto dto);

}
