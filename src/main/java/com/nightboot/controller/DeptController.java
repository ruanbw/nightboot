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
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "部门管理")
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @Operation(summary = "查询部门列表")
    @GetMapping
    public Result<List<DeptListVo>> findAll(DeptPageDto dto) {
        return Result.success(deptService.findAll(dto));
    }

    @Operation(summary = "查询部门详情")
    @GetMapping("{deptId}")
    public Result<DeptInfoVo> findOne(@PathVariable("deptId")@ApiParam(value = "部门ID") String deptId){
        return Result.success(deptService.findOne(deptId));
    }

    @Operation(summary = "保存部门")
    @PostMapping
    public Result<Void> save(@RequestBody @Validated SaveDeptDto dto) {
        deptService.save(dto);
        return Result.success();
    }

    @Operation(summary = "更新部门")
    @PutMapping
    public Result<Void> update(@RequestBody @Validated UpdateDeptDto dto) {
        deptService.update(dto);
        return Result.success();
    }

    @Operation(summary = "删除部门")
    @DeleteMapping("{id}")
    public Result<Void> del(@PathVariable("id") @ApiParam(value = "部门ID") String id) {
        deptService.del(id);
        return Result.success();
    }
}
