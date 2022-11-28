package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.Dept;
import com.muyuan.user.domain.repo.DeptRepo;
import com.muyuan.user.face.dto.DeptQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.DeptConverter;
import com.muyuan.user.infrastructure.repo.dataobject.DeptDO;
import com.muyuan.user.infrastructure.repo.dataobject.MenuDO;
import com.muyuan.user.infrastructure.repo.mapper.DeptMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;

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
        List<DeptDO> deptDOS = deptMapper.selectList(new SqlBuilder(MenuDO.class)
                .like(NAME, command.getName())
                .eq(STATUS, command.getStatus())
                .orderByAsc(ORDER_NUM)
                .build());

        return converter.to(deptDOS);
    }

    @Override
    public Dept selectDept(Long id) {
        DeptDO deptDO = deptMapper.selectOne(new SqlBuilder(DeptDO.class)
                .eq(ID, id)
                .build());
        return converter.to(deptDO);
    }

    @Override
    public Dept selectDept(Dept.Identify identify) {
        DeptDO deptDO = deptMapper.selectOne(new SqlBuilder(DeptDO.class).select(ID)
                .eq(NAME, identify.getName())
                .eq(PARENT_ID, identify.getParentId())
                .eq(ID, identify.getId())
                .build());

        return converter.to(deptDO);
    }

    @Override
    public boolean addDept(Dept dept) {
        DeptDO to = converter.to(dept);
        Integer count = deptMapper.insertAuto(to);
        return count > 0;
    }

    @Override
    public Dept updateDept(Dept dept) {
        SqlBuilder sqlBuilder = new SqlBuilder(DeptDO.class)
                .eq(ID, dept.getId());

        DeptDO menuDO = deptMapper.selectOne(sqlBuilder.build());
        if (ObjectUtils.isNotEmpty(menuDO)) {
            deptMapper.updateBy(converter.to(dept), ID);
        }

        return converter.to(menuDO);
    }

}
