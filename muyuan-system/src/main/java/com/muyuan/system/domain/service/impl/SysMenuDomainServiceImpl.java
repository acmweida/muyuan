package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.entity.SysMenuEntity;
import com.muyuan.system.domain.entity.SysRoleEntity;
import com.muyuan.system.domain.factories.SysMenuFactory;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.query.SysMenuQuery;
import com.muyuan.system.domain.repo.SysMenuRepo;
import com.muyuan.system.domain.service.SysMenuDomainService;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.interfaces.assembler.SysMenuAssembler;
import com.muyuan.system.interfaces.dto.SysMenuDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName SysMenuServiceImpl
 * Description 菜单服务实现
 * @Author 2456910384
 * @Date 2022/4/15 14:17
 * @Version 1.0
 */
@Component
@AllArgsConstructor
@Slf4j
public class SysMenuDomainServiceImpl implements SysMenuDomainService {

    private SysMenuQuery sysMenuQuery;

    private SysMenuRepo sysMenuRepo;

    @Override
    public List<SysMenu> list(SysMenuDTO sysMenuDTO) {
        return sysMenuQuery.list(sysMenuDTO);
    }

    @Override
    public Optional<SysMenu> get(String id) {
        SysMenu sysMenu = sysMenuQuery.get(new SysMenu(Long.valueOf(id)));
        if (null != sysMenu) {
            return Optional.of(sysMenu);
        }
        return Optional.empty();
    }

    /**
     * 通过角色名称查询权限
     * @param roleNames
     * @return
     */
    public Set<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        Set<String> perms = new HashSet<>();
        if (SysRoleEntity.isAdmin(roleNames)) {
            perms.add(SecurityConst.ALL_PERMISSION);
        } else {
            perms = sysMenuQuery.selectMenuPermissionByRoleNames(roleNames);
        }
        return perms;
    }

    @Override
    public List<SysMenu> selectMenuByRoleNames(List<String> roleNames) {
        if (ObjectUtils.isEmpty(roleNames)) {
            return Collections.EMPTY_LIST;
        }
        return sysMenuQuery.selectMenuByRoleNames(roleNames);
    }

    @Override
    public int add(SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = SysMenuFactory.newSysMenu(sysMenuDTO);
        return sysMenuRepo.insert(sysMenu);
    }

    @Override
    public int update(SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = SysMenuFactory.buildSysMenu(sysMenuDTO);
        sysMenu.setUpdateTime(new Date());
        sysMenu.setUpdateBy(SecurityUtils.getUserId());
        return sysMenuRepo.updateById(sysMenu);
    }

    @Override
    public String checkMenuNameUnique(SysMenu sysMenu) {
        Long id = null == sysMenu.getId() ? 0 : sysMenu.getId();
        sysMenu = sysMenuRepo.selectOne(new SqlBuilder(SysMenu.class)
                .eq("name",sysMenu.getName())
                .eq("parentId",sysMenu.getParentId())
                .build());
        if (null != sysMenu && id.equals(sysMenu.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public int deleteById(String... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return 0;
        }
        return sysMenuRepo.deleteById(ids);
    }
}
