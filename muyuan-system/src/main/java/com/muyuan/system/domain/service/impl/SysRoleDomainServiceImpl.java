package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.entity.SysRoleEntity;
import com.muyuan.system.domain.factories.SysRoleFactory;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysRoleMenu;
import com.muyuan.system.domain.model.SysUserRole;
import com.muyuan.system.domain.repo.SysMenuRepo;
import com.muyuan.system.domain.repo.SysRoleRepo;
import com.muyuan.system.domain.service.SysRoleDomainService;
import com.muyuan.system.interfaces.dto.SysRoleDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

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

    private SysMenuRepo sysMenuRepo;

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        Assert.notNull(userId, "user Id is null");
        return sysRoleRepo.selectRoleByUserId(userId);
    }

    @Override
    public Page list(SysRoleDTO sysRoleDTO) {
        SqlBuilder sqlBuilder = new SqlBuilder(SysRole.class)
                .eq("name", sysRoleDTO.getName())
                .eq("status", sysRoleDTO.getStatus())
                .orderByAsc("orderNum");

        Page page = Page.builder().build();
        if (sysRoleDTO.isEnablePage()) {
            page.setPageNum(sysRoleDTO.getPageNum());
            page.setPageSize(sysRoleDTO.getPageSize());
            sqlBuilder.page(page);
        }

        List<SysRole> list = sysRoleRepo.select(sqlBuilder.build());

        page.setRows(list);

        return page;
    }

    @Override
    public void selectUser(Long roleId,Long[] userIds) {
        FunctionUtil.getIfNotNullThen(
                () -> userIds,
                (v) -> {
                    List<SysUserRole> set = new ArrayList<>(userIds.length);
                    for (Long userId : userIds) {
                        set.add(SysUserRole.builder().roleId(roleId).userId(userId).build());
                    }
                    sysRoleRepo.batchInsertRole(set);
                }
        );
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

        SysRole sysRole = SysRoleFactory.newInstance(sysRoleDTO);
        sysRoleRepo.insert(sysRole);

        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(sysRoleDTO.getMenuIds())) {
            Arrays.stream(sysRoleDTO.getMenuIds()).forEach(
                    item -> {
                        sysRoleMenus.add(new SysRoleMenu(sysRole.getId(), Long.valueOf(item)));
                    }
            );
        }
        sysRoleRepo.batchInsert(sysRoleMenus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleDTO sysRoleDTO) {
        SysRoleEntity sysRole = sysRoleDTO.convert();
        sysRole.update();
        sysRoleRepo.updateById(sysRole);

        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(sysRoleDTO.getMenuIds())) {
            Arrays.stream(sysRoleDTO.getMenuIds()).forEach(
                    item -> {
                        sysRoleMenus.add(new SysRoleMenu(sysRole.getId(), Long.valueOf(item)));
                    }
            );
        }

        sysRoleRepo.deleteMenuByRoleId(sysRole.getId());

        sysRoleRepo.batchInsert(sysRoleMenus);

        sysMenuRepo.refreshCache(sysRole.getCode());

    }


    @Override
    public Optional<SysRole> getById(String id) {
        SqlBuilder sqlBuilder = new SqlBuilder(SysRole.class)
                .eq("id", id);

        SysRole sysRole = sysRoleRepo.selectOne(sqlBuilder.build());

        if (null == sysRole) {
            return Optional.empty();
        }
        return Optional.of(sysRole);
    }

    @Override
    public void deleteById(String... id) {
        sysRoleRepo.deleteById(id);
    }
}
