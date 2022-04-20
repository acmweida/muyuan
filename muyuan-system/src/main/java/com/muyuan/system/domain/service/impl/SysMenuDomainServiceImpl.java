package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.entity.SysRoleEntity;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.query.SysMenuQuery;
import com.muyuan.system.domain.repo.SysMenuRepo;
import com.muyuan.system.domain.service.SysMenuDomainService;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.interfaces.dto.SysMenuDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        SysMenu sysMenu = sysMenuQuery.get(new SysMenu(id));
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
    public int add(SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuDTO,sysMenu);
        return sysMenuRepo.insert(sysMenu);
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
}
