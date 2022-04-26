package com.muyuan.system.domain.service.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.repo.SysRoleRepo;
import com.muyuan.system.domain.service.SysRoleDomainService;
import com.muyuan.system.interfaces.dto.SysRoleDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.core.util.Assert;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName SysRoleDomainServiceImpl
 * Description 角色域服务
 * @Author 2456910384
 * @Date 2022/4/18 15:41
 * @Version 1.0
 */
@Component
@AllArgsConstructor
@Slf4j
public class SysRoleDomainServiceImpl implements SysRoleDomainService {


    private SysRoleRepo sysRoleRepo;

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        Assert.isNonEmpty(userId);
        return sysRoleRepo.selectRoleByUserId(userId);
    }

    @Override
    public Page list(SysRoleDTO sysRoleDTO) {
        SqlBuilder sqlBuilder = new SqlBuilder(SysRole.class);
        if (ObjectUtils.isNotEmpty(sysRoleDTO.getName())) {
            sqlBuilder.eq("name",sysRoleDTO.getName());
        }
        if (ObjectUtils.isNotEmpty(sysRoleDTO.getStatus())) {
            sqlBuilder.eq("status",sysRoleDTO.getStatus());
        }

        Page page = new Page();
        page.setPageNum(sysRoleDTO.getPageNum());
        page.setPageSize(sysRoleDTO.getPageSize());
        sqlBuilder.page(page);

        List<SysRole> list = sysRoleRepo.select(sqlBuilder.build());

        page.setRows(list);

        return page;
    }
}
