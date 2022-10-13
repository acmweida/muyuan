package com.muyuan.user.domain.service.impl;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.user.domain.model.entity.user.Menu;
import com.muyuan.user.domain.model.valueobject.RoleCode;
import com.muyuan.user.domain.repo.MenuRepo;
import com.muyuan.user.domain.service.MenuDomainService;
import com.muyuan.user.face.dto.MenuQueryCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<Menu> getMenuByRoleCodes(MenuQueryCommand request) {
        RoleCode[] roleCodes = request.getRoleCodes();
        List<Menu> menus = new ArrayList<>();
        for (RoleCode roleCode : roleCodes) {

            List<Menu> roleMenus = CacheServiceUtil.getAndUpdateList(redisCacheService, RedisConst.SYS_ROLE_MENU_KEY_PREFIX + roleCode,
                    () -> menuRepo.selectByRoleCode(roleCode, request.getPlatformType()), Menu.class);

            menus.addAll(roleMenus);
        }
        //* TODO: 去重
        return menus;
    }
}
