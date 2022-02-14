package com.muyuan.member.infrastructure.persistence;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.JdbcValueConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.util.RedisUtils;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.repo.MenuRepo;
import com.muyuan.member.infrastructure.persistence.dao.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName MenuRepoImpl
 * Description MenuRepoImpl
 * @Author 2456910384
 * @Date 2022/2/11 16:39
 * @Version 1.0
 */
@Component
public class MenuRepoImpl implements MenuRepo {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public List<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        List<String> perms = new ArrayList<>();
        Iterator<String> it = roleNames.iterator();

        // 查询缓存
        while (it.hasNext()) {
            Set<Object> rolePerms = redisUtils.sGet(RedisConst.ROLE_PERM_KEY_PREFIX + it.next());
            if (null != rolePerms) {
                perms.addAll(roleNames);
                it.remove();
            }
        }

        for (String roleName : roleNames) {
            if (StrUtil.isNotEmpty(roleName)) {
                List<String> roleMenus = selectMenuPermissionByRoleName(roleName);
                perms.addAll(roleMenus);
                // 更新缓存
                redisUtils.sSet(RedisConst.ROLE_PERM_KEY_PREFIX + roleNames, roleMenus);
            }
        }
        return perms;
    }

    @Override
    public List<String> selectMenuPermissionByRoleName(String roleName) {
        return menuMapper.selectMenuPermissionByRoleNames(Arrays.asList(roleName));
    }

    @Override
    public List<Menu> selectMenuByRoleNames(List<String> roleNames) {
        List<Menu> menus = new ArrayList<>();
        Iterator<String> it = roleNames.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String cacheMenuJson = (String) redisUtils.get(RedisConst.ROLE_MENU_KEY_PREFIX + it.next());
            if (null != cacheMenuJson) {
                menus.addAll(JSONUtil.parseObjectList(cacheMenuJson, ArrayList.class, Menu.class));
                it.remove();
            }
        }

        for (String roleName : roleNames) {
            if (StrUtil.isNotEmpty(roleName)) {
                List<Menu> roleMenus = selectMenuByRoleName(roleName);
                menus.addAll(roleMenus);
                // 更新缓存
                redisUtils.set(RedisConst.ROLE_MENU_KEY_PREFIX + roleNames, JSONUtil.toJsonString(roleMenus));
            }
        }
        return menus;
    }

    @Override
    public List<Menu> selectMenuByRoleName(String roleName) {
        return menuMapper.selectMenuByRoleNames(Arrays.asList(roleName));
    }
}
