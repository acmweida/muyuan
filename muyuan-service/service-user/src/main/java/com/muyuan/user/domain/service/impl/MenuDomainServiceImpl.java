package com.muyuan.user.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.thread.CommonThreadPool;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.MenuID;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.repo.MenuRepo;
import com.muyuan.user.domain.repo.RoleRepo;
import com.muyuan.user.domain.service.MenuDomainService;
import com.muyuan.user.face.dto.MenuCommand;
import com.muyuan.user.face.dto.MenuQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @ClassName MenuDomainServiceImpl
 * Description 权限
 * @Author 2456910384
 * @Date 2022/10/12 14:55
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class MenuDomainServiceImpl implements MenuDomainService {

    private MenuRepo menuRepo;

    private RoleRepo roleRepo;

    private RedisCacheService redisCacheService;

    @Override
    public List<Menu> getMenuByRoleCodes(MenuQueryCommand command) {
        RoleCode[] roleCodes = command.getRoleCodes();
        List<Menu> menus = new ArrayList<>();
        for (RoleCode roleCode : roleCodes) {

            List<Menu> roleMenus = CacheServiceUtil.getAndUpdateList(redisCacheService,
                    getRoleMenuKeyPrefix(command.getPlatformType()) + roleCode.getValue(),
                    () -> menuRepo.selectByRoleCode(roleCode, command.getPlatformType()), Menu.class);

            menus.addAll(roleMenus);
        }
        // 去重
        return menus.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<Menu>(Comparator.comparing(Menu::getId))), ArrayList::new)
        ).stream().sorted(Comparator.comparing(Menu::getOrderNum)).collect(Collectors.toList());
    }

    @Override
    public List<Menu> list(MenuQueryCommand command) {
        if (ObjectUtils.isEmpty(command.getPlatformType())) {
            command.setPlatformType(PlatformType.MEMBER);
        }
        List<Menu> list = menuRepo.list(command);
        return list;
    }

    @Override
    public Optional<Menu> getMenu(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return menuRepo.selectMenu(new MenuID(id_));
                });
    }

    @Override
    public String checkUnique(Menu.Identify key) {
        Long id = null == key.getId() ? 0 : key.getId().getValue();
        Menu menu = menuRepo.selectMenu(key);
        if (null != menu && !id.equals(menu.getId().getValue())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public boolean updateMenu(MenuCommand command) {
        if (ObjectUtils.isEmpty(command.getId())) {
            return false;
        }

        Menu menu = new Menu();

        menu.setId(command.getId());
        menu.setName(command.getName());
        menu.setParentId(command.getParentId());
        menu.setOrderNum(command.getOrderNum());
        menu.setPath(command.getPath());
        menu.setComponent(command.getComponent());
        menu.setQuery(command.getQuery());
        menu.setFrame(command.getFrame());
        menu.setType(command.getType());
        menu.setVisible(command.getVisible());
        menu.setStatus(command.getStatus());
        menu.setIcon(command.getIcon());
        menu.setRemark(command.getRemark());
        menu.setCache(command.getCache());
        menu.setUpdateTime(DateTime.now().toDate());
        menu.setUpdateBy(command.getUpdateBy());

        Menu old = menuRepo.updateDMenu(menu);
        if (ObjectUtils.isNotEmpty(old)) {
            List<Role> roles = roleRepo.selectByMenuID(old.getId());
            for (Role role : roles) {
                redisCacheService.delayDoubleDel(getRoleMenuKeyPrefix(old.getPlatformType()) + role.getCode());
                redisCacheService.delayDoubleDel(getRoleMenuKeyPrefix(menu.getPlatformType()) + role.getCode());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addMenu(MenuCommand command) {
        Menu menu = new Menu();

        menu.setName(command.getName());
        menu.setParentId(command.getParentId());
        menu.setOrderNum(command.getOrderNum());
        menu.setPath(command.getPath());
        menu.setComponent(command.getComponent());
        menu.setQuery(command.getQuery());
        menu.setFrame(command.getFrame());
        menu.setType(command.getType());
        menu.setVisible(command.getVisible());
        menu.setStatus(command.getStatus());
        menu.setIcon(command.getIcon());
        menu.setRemark(command.getRemark());
        menu.setCache(command.getCache());
        menu.setCreateTime(DateTime.now().toDate());
        menu.setCreateBy(command.getUpdateBy());

        return menuRepo.addMenu(menu);
    }

    @Override
    @Transactional
    public boolean deleteMenuById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        List<Long> removeIds = new ArrayList<>(Arrays.asList(ids));
        List<Menu> menuList;
        do {
            menuList = menuRepo.list(MenuQueryCommand.builder().parentIds(ids).build());
            if (!menuList.isEmpty()) {
                List<Long> collect = menuList.stream().map(Menu::getId).map(MenuID::getValue)
                        .collect(Collectors.toList());
                removeIds.addAll(collect);
                ids = collect.toArray(new Long[0]);
            }
        } while (!menuList.isEmpty());

        List<Menu> olds = menuRepo.deleteBy(removeIds.toArray(new Long[0]));
        menuRepo.deleteRef(olds.stream().map(Menu::getId).toArray(MenuID[]::new));

        Runnable task = () -> {
            for (Menu old : olds) {
                List<Role> roles = roleRepo.selectByMenuID(old.getId());
                if (ObjectUtils.isNotEmpty(roles)) {
                    for (Role role : roles) {
                        redisCacheService.delayDoubleDel(getRoleMenuKeyPrefix(old.getPlatformType()) + role.getCode());
                    }
                }
            }
        };

        CommonThreadPool.exec(task);

        return !olds.isEmpty();
    }


    private String getRoleMenuKeyPrefix(PlatformType platformType) {
        switch (platformType) {
            case OPERATOR:
                return RedisConst.OPERATOR_ROLE_MENU_KEY_PREFIX;
            case MEMBER:
                return RedisConst.MEMBER_ROLE_MENU_KEY_PREFIX;
            case MERCHANT:
                return RedisConst.MERCHANT_ROLE_MENU_KEY_PREFIX;
        }
        return "";
    }
}
