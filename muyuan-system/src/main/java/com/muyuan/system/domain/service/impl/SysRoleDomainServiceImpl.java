package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.factories.SysRoleFactory;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysRoleMenu;
import com.muyuan.system.domain.repo.SysRoleRepo;
import com.muyuan.system.domain.service.SysRoleDomainService;
import com.muyuan.system.interfaces.dto.SysRoleDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
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
        Assert.notNull(userId,"user Id is null");
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

    @Override
    public String checkRoleCodeUnique(SysRole sysRole) {
        Long id = null == sysRole.getId() ? 0 : sysRole.getId();
        sysRole = sysRoleRepo.selectOne(new SqlBuilder(SysRole.class).select("id")
                .eq("code", sysRole.getCode())
                .build());
        if (null != sysRole && !id.equals(sysRole.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRoleDTO sysRoleDTO) {

        SysRole sysRole = SysRoleFactory.newSysRole(sysRoleDTO);
        sysRoleRepo.insert(sysRole);

        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(sysRoleDTO.getMenuIds())) {
            Arrays.stream(sysRoleDTO.getMenuIds()).forEach(
                    item -> {
                        sysRoleMenus.add(new SysRoleMenu(sysRole.getId(),Long.valueOf(item)));
                    }
            );
        }
        sysRoleRepo.batchInsert(sysRoleMenus);
    }
}
