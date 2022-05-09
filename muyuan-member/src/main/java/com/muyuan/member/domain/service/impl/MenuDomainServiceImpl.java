package com.muyuan.member.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.member.domain.entity.RoleEntity;
import com.muyuan.member.domain.factories.MenuFactory;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.query.MenuQuery;
import com.muyuan.member.domain.repo.MenuRepo;
import com.muyuan.member.domain.service.MenuDomainService;
import com.muyuan.member.interfaces.dto.MenuDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName MenuQueryImpl
 * Description 权限查询
 * @Author 2456910384
 * @Date 2022/2/9 16:52
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class MenuDomainServiceImpl implements MenuDomainService {

    public MenuRepo menuRepo;;

    public MenuQuery menuQuery;

    /**
     * 通过角色名称查询权限
     * @param roleCodes
     * @return
     */
    public Set<String> selectMenuPermissionByRoleCodes(List<String> roleCodes) {
        Set<String> perms = new HashSet<>();
        if (RoleEntity.isShopKeeper(roleCodes)) {
            perms.add(SecurityConst.ALL_PERMISSION);
        } else {
             perms = menuQuery.selectMenuPermissionByRoleNames(roleCodes);
        }
        return perms;
    }

    @Override
    public String checkMenuNameUnique(Menu menu) {
        Long id = null == menu.getId() ? 0 : menu.getId();
        menu = menuRepo.selectOne(new SqlBuilder(Menu.class)
                .eq("name",menu.getName())
                .eq("parentId",menu.getParentId())
                .build());
        if (null != menu && id.equals(menu.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    /**
     * 通过角色名称查询目录 菜单
     *
     * @param roleNames
     * @return
     */
    @Override
    public List<Menu> selectMenuByRoleNames(List<String> roleNames) {
        if (ObjectUtils.isEmpty(roleNames)) {
            return Collections.EMPTY_LIST;
        }
        return menuRepo.selectMenuByRoleCodes(roleNames);
    }

    @Override
    public List<Menu> list(MenuDTO sysMenuDTO) {
        SqlBuilder sqlBuilder = new SqlBuilder(Menu.class);
        if (StrUtil.isNotBlank(sysMenuDTO.getName())) {
            sqlBuilder.eq("name", sysMenuDTO.getName());
        }
        if (StrUtil.isNotBlank(sysMenuDTO.getStatus())) {
            sqlBuilder.eq("status", sysMenuDTO.getStatus());
        }
        List<Menu> list = menuRepo.select(sqlBuilder.build());
        return list;
    }

    @Override
    public List<Long> listSelectIdByRoleId(String... roleIds) {
        if (ObjectUtils.isEmpty(roleIds)) {
            return Collections.EMPTY_LIST;
        }
        return menuRepo.listByRoleId(roleIds).stream().map(Menu::getId).collect(Collectors.toList());
    }

    /**
     * 查询
     * condition
     *      id
     *      name
     * @param sysMenu
     * @return
     */
    public Menu get(Menu sysMenu) {
        SqlBuilder sqlBuilder = new SqlBuilder(Menu.class);
        sqlBuilder.eq("status", "0");
        if (!Objects.isNull(sysMenu.getId())) {
            sqlBuilder.eq("id", sysMenu.getId());
        }
        if (!Objects.isNull(sysMenu.getName())) {
            sqlBuilder.eq("name", sysMenu.getName());
        }
        return sysMenu = menuRepo.selectOne(sqlBuilder.build());
    }

    @Override
    public Optional<Menu> get(String id) {
        Menu sysMenu = get(new Menu(Long.valueOf(id)));
        if (null != sysMenu) {
            return Optional.of(sysMenu);
        }
        return Optional.empty();
    }

    // ##############################  query ########################## //

    @Override
    public void add(MenuDTO sysMenuDTO) {
        Menu sysMenu = MenuFactory.newSysMenu(sysMenuDTO);
        menuRepo.insert(sysMenu);
        menuRepo.refreshCache();
    }

    @Override
    public void update(MenuDTO sysMenuDTO) {
        Menu sysMenu = MenuFactory.updateSysMenu(sysMenuDTO);
        menuRepo.updateById(sysMenu);
        menuRepo.refreshCache();
    }



    @Override
    public void deleteById(String... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return ;
        }
        menuRepo.deleteById(ids);
        menuRepo.refreshCache();
    }




}
