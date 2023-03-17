package com.muyuan.user.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.user.domain.model.entity.Dept;
import com.muyuan.user.domain.repo.DeptRepo;
import com.muyuan.user.face.dto.DeptQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.DeptConverter;
import com.muyuan.user.infrastructure.repo.dataobject.DeptDO;
import com.muyuan.user.infrastructure.repo.mapper.DeptMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MenuRepoImpl
 * Description MenuRepoImpl
 * @Author 2456910384
 * @Date 2022/10/13 14:13
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class DeptRepoImpl implements DeptRepo {

    private DeptMapper deptMapper;

    private DeptConverter converter;

    @Override
    public List<Dept> list(DeptQueryCommand command) {

        List<DeptDO> deptDOS = deptMapper.selectList(new LambdaQueryWrapper<DeptDO>()
                .like(DeptDO::getName, command.getName())
                .eq(DeptDO::getStatus, command.getStatus())
                .orderByAsc(DeptDO::getOrderNum)
        );

        return converter.to(deptDOS);
    }

    @Override
    public Dept selectDept(Long id) {
        DeptDO deptDO = deptMapper.selectById(id);
        return converter.to(deptDO);
    }

    @Override
    public Dept selectDept(Dept.Identify identify) {
        DeptDO deptDO = deptMapper.selectOne(new LambdaQueryWrapper<DeptDO>()
                .select(DeptDO::getId)
                .eq(ObjectUtils.isEmpty(identify.getId()), DeptDO::getId, identify.getId())
                .eq(DeptDO::getName, identify.getName())
                .eq(DeptDO::getParentId, identify.getParentId()));


        return converter.to(deptDO);
    }

    @Override
    public boolean addDept(Dept dept) {
        DeptDO to = converter.to(dept);
        Integer count = deptMapper.insert(to);
        dept.setId(to.getId());
        return count > 0;
    }

    @Override
    public Dept updateDept(Dept dept) {

        DeptDO menuDO = deptMapper.selectById(dept.getId());
        if (ObjectUtils.isNotEmpty(menuDO)) {
            deptMapper.updateById(converter.to(dept));
        }

        return converter.to(menuDO);
    }

}
