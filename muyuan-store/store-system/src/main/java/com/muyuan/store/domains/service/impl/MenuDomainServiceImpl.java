package com.muyuan.store.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.store.domains.dto.MenuDTO;
import com.muyuan.store.domains.factories.MenuFactory;
import com.muyuan.store.domains.model.Menu;
import com.muyuan.store.domains.repo.MenuRepo;
import com.muyuan.store.domains.service.MenuDomainService;
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

    public MenuRepo menuRepo;

    /**
     * 通过角色名称查询权限
     *
     * @param roleCodes
     * @return
     */
    @Override
    public Set<String> selectMenuPermissionByRoleCodes(List<String> roleCodes) {
        List<String> permList = menuRepo.selectMenuPermissionByRoleCodes(roleCodes);
        return new HashSet<>(permList);
    }

    @Override
    public String checkMenuNameUnique(Menu menu) {
        Long id = null == menu.getId() ? 0 : menu.getId();
        menu.setId(null);
        menu = menuRepo.selectOne(menu);
        if (null != menu && !id.equals(menu.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    /**
     * 通过角色名称查询目录 菜单
     *
     * @param roleCodes
     * @return
     */
    @Override
    public List<Menu> selectMenuByRoleCodes(List<String> roleCodes) {
        if (ObjectUtils.isEmpty(roleCodes)) {
            return Collections.EMPTY_LIST;
        }
        return menuRepo.selectMenuByRoleCodes(roleCodes);
    }

    @Override
    public List<Menu> list(MenuDTO menuDTO) {
        List<Menu> list = menuRepo.select(menuDTO);
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
     * id
     * name
     *
     * @param menu
     * @return
     */
    public Menu get(Menu menu) {
        return menuRepo.selectOne(Menu.builder()
                .id(menu.getId())
                .name(menu.getName())
                .build());
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
            return;
        }
        menuRepo.deleteById(ids);
        menuRepo.refreshCache();
    }


}
