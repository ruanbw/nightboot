package com.nightboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nightboot.common.exception.ServiceException;
import com.nightboot.common.utils.IdUtils;
import com.nightboot.common.utils.SecurityUtils;
import com.nightboot.common.utils.StringUtils;
import com.nightboot.domain.po.DeptPo;
import com.nightboot.domain.req.dept.DeptPageDto;
import com.nightboot.domain.req.dept.SaveDeptDto;
import com.nightboot.domain.req.dept.UpdateDeptDto;
import com.nightboot.domain.res.dept.DeptListVo;
import com.nightboot.mapper.DeptMapper;
import com.nightboot.service.DeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptPo> implements DeptService {
    @Override
    public List<DeptListVo> findAll(DeptPageDto dto) {
        //一次把所有的数据都查出来
        List<DeptListVo> regions = this.baseMapper.findAll(dto);
        //指定根节点的parentId
        return buildChildren(null, regions);
    }

    @Override
    public void save(SaveDeptDto dto) {
        DeptPo dept = query().eq("dept_name", dto.getDeptName()).one();
        if (StringUtils.isNotNull(dept)) {
            throw new ServiceException("部门已存在");
        }
        DeptPo newDept = new DeptPo();
        BeanUtils.copyProperties(dto, newDept);
        newDept.setId(IdUtils.nextId());
        newDept.setCreateBy(SecurityUtils.getUserId());
        save(newDept);
    }

    @Override
    public void update(UpdateDeptDto dto) {
        DeptPo dept = getById(dto.getId());
        if (StringUtils.isNull(dept)) {
            throw new ServiceException("部门不存在");
        }
        BeanUtils.copyProperties(dto, dept);
        dept.setUpdateBy(SecurityUtils.getUserId());
        updateById(dept);
    }

    @Override
    public void del(String id) {
        DeptPo dept = getById(id);

        if (StringUtils.isNull(dept)) {
            throw new ServiceException("部门不存在");
        }
        DeptPo childrenDept = query().eq("parent_id", id).one();
        if (StringUtils.isNotNull(childrenDept)) {
            throw new ServiceException("请先删除下级部门");
        }
        dept.setStatus(2);
        dept.setUpdateBy(SecurityUtils.getUserId());
        updateById(dept);
    }

    private List<DeptListVo> buildChildren(String parentId, List<DeptListVo> allList) {
        List<DeptListVo> voList = new ArrayList<>();
        for (DeptListVo item : allList) {
            //如果相等
            if (StringUtils.equals(item.getParentId(), parentId)) {
                //递归，自己调自己
                item.setChildren(buildChildren(item.getId(), allList));
                voList.add(item);
            }
        }
        return voList;
    }

}
