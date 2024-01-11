package com.nightboot.controller;

import com.nightboot.common.annotation.Anonymous;
import com.nightboot.domain.Result;
import com.nightboot.domain.po.DeptPo;
import com.nightboot.domain.req.dept.DeptPageDto;
import com.nightboot.domain.req.dept.SaveDeptDto;
import com.nightboot.domain.req.dept.UpdateDeptDto;
import com.nightboot.domain.res.dept.DeptInfoVo;
import com.nightboot.domain.res.dept.DeptListVo;
import com.nightboot.service.DeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping
    public Result<List<DeptListVo>> findAll(DeptPageDto dto) {
        return Result.success(deptService.findAll(dto));
    }

    @GetMapping("{deptId}")
    public Result<DeptInfoVo> findOne(@PathVariable("deptId") String deptId){
        return Result.success(deptService.findOne(deptId));
    }
    @PostMapping
    public Result<Void> save(@RequestBody SaveDeptDto dto) {
        deptService.save(dto);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody UpdateDeptDto dto) {
        deptService.update(dto);
        return Result.success();
    }

    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable("id") String id) {
        deptService.del(id);
        return Result.success();
    }
}
