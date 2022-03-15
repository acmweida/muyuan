package com.muyuan.member.infrastructure.persistence;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.JdbcValueConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheManager;
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
    RedisCacheManager redisCacheManager;

    @Override
    public List<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        List<String> perms = new ArrayList<>();
        Iterator<String> it = roleNames.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleName = it.next();
            if (StrUtil.isNotEmpty(roleName)) {
//                redisCacheManager.redisUtils.del(RedisConst.ROLE_PERM_KEY_PREFIX+roleName);
                Set<String> rolePerms = redisCacheManager.sGet(RedisConst.ROLE_PERM_KEY_PREFIX, roleName, () -> new HashSet(selectMenuPermissionByRoleName(roleName)));
                if (null != rolePerms) {
                    perms.addAll(rolePerms);
                }
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
            String roleName = it.next();
//            redisCacheManager.redisUtils.del(RedisConst.ROLE_MENU_KEY_PREFIX+roleName);
            String cacheMenuJson = (String) redisCacheManager.get(RedisConst.ROLE_MENU_KEY_PREFIX, roleName,
                    () -> JSONUtil.toJsonString(selectMenuByRoleName(roleName))
            );
            if (StrUtil.isNotEmpty(cacheMenuJson)) {
                menus.addAll(JSONUtil.parseObjectList(cacheMenuJson, ArrayList.class, Menu.class));
            }
        }
        return menus;
    }

    @Override
    public List<Menu> selectMenuByRoleName(String roleName) {
        return menuMapper.selectMenuByRoleNames(Arrays.asList(roleName));
    }
}
