package com.muyuan.manager.system.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.exception.ArgumentException;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.system.domains.factories.SysDeptFactory;
import com.muyuan.manager.system.domains.model.SysDept;
import com.muyuan.manager.system.domains.repo.SysDeptRepo;
import com.muyuan.manager.system.service.SysDeptDomainService;
import com.muyuan.manager.system.dto.SysDeptDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SysDeptDomainServiceImpl
 * Description SysDeptDomainServiceImpl
 * @Author 2456910384
 * @Date 2022/5/13 11:08
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class DeptServiceImpl implements SysDeptDomainService {

    private SysDeptRepo sysDeptRepo;


    @Override
    public List<SysDept> list(SysDeptDTO sysDeptDTO) {
        SqlBuilder sqlBuilder = new SqlBuilder(SysDept.class)
                .eq("name", sysDeptDTO.getName())
                .eq("status", sysDeptDTO.getStatus())
                .orderByAsc("orderNum");
        List<SysDept> list = sysDeptRepo.select(sqlBuilder.build());
        return list;
    }

    @Override
    public String checkUnique(SysDept sysRole) {
        Long id = null == sysRole.getId() ? 0 : sysRole.getId();
        sysRole = sysDeptRepo.selectOne(new SqlBuilder(SysDept.class).select("id")
                .eq("name", sysRole.getName())
                .eq("parentId",sysRole.getParentId())
                .build());
        if (null != sysRole && !id.equals(sysRole.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    @Transactional
    public void add(SysDeptDTO sysDeptDTO) {
        SysDept parentDept = (SysDept) FunctionUtil.getIfNullThen(
                () -> sysDeptRepo.selectOne(new SqlBuilder(SysDept.class)
                        .eq("id", sysDeptDTO.getParentId())
                        .build()),
                () -> {
                    throw new ArgumentException(StrUtil.format("部门id:{}没有找到",sysDeptDTO.getParentId()));
                }
        ).get();

        SysDept sysDept = SysDeptFactory.newInstance(sysDeptDTO);

        sysDeptRepo.insert(sysDept);
        sysDept.setParent(parentDept);
        sysDept.rebuildAncestors();

        sysDeptRepo.updateById(sysDept);

    }
}
