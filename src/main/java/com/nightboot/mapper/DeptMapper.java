package com.nightboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nightboot.domain.po.DeptPo;
import com.nightboot.domain.req.dept.DeptPageDto;
import com.nightboot.domain.res.dept.DeptListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper extends BaseMapper<DeptPo> {

    List<DeptListVo> findAll(@Param("q") DeptPageDto dto);

}
