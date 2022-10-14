package com.muyuan.user.domain.service.impl;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.user.domain.model.entity.Menu;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.repo.MenuRepo;
import com.muyuan.user.domain.service.MenuDomainService;
import com.muyuan.user.face.dto.MenuQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

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
