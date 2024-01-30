package com.nightboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nightboot.domain.po.DeptPo;
import com.nightboot.domain.req.dept.DeptPageDto;
import com.nightboot.domain.req.dept.SaveDeptDto;
import com.nightboot.domain.req.dept.UpdateDeptDto;
import com.nightboot.domain.res.dept.DeptInfoVo;
import com.nightboot.domain.res.dept.DeptListVo;

import java.util.List;

public interface DeptService extends IService<DeptPo> {

    List<DeptListVo> findAll(DeptPageDto dto);

    DeptInfoVo findOne(String deptId);

    void save(SaveDeptDto dto);

    void update(UpdateDeptDto dto);

    void del(String id);
}
