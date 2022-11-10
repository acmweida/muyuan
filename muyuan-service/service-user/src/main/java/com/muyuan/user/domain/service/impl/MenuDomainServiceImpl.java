package com.muyuan.user.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.repo.MenuRepo;
import com.muyuan.user.domain.service.MenuDomainService;
import com.muyuan.user.face.dto.MenuCommand;
import com.muyuan.user.face.dto.MenuQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.*;

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
        );
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
                    return menuRepo.selectMenu(id_);
                });
    }

    @Override
    public String checkUnique(Menu menu) {
        Long id = null == menu.getId() ? 0 : menu.getId().getValue();
        menu = menuRepo.selectMenu(menu);
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
            if (!command.getName().equals(old.getType())) {
                redisCacheService.delayDoubleDel(RedisConst.SYS_ROLE_PERM_KEY_PREFIX + RedisConst.ALL_PLACE_HOLDER);
                redisCacheService.delayDoubleDel(RedisConst.OPERATOR_ROLE_MENU_KEY_PREFIX + RedisConst.ALL_PLACE_HOLDER);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addMenu(MenuCommand command) {
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
        menu.setCreateTime(DateTime.now().toDate());
        menu.setCreateBy(command.getUpdateBy());

        redisCacheService.delayDoubleDel(RedisConst.SYS_ROLE_PERM_KEY_PREFIX + RedisConst.ALL_PLACE_HOLDER);
        redisCacheService.delayDoubleDel(RedisConst.OPERATOR_ROLE_MENU_KEY_PREFIX + RedisConst.ALL_PLACE_HOLDER);
        return  menuRepo.addMenu(menu);
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
