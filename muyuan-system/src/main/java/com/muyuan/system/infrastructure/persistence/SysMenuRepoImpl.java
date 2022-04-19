package com.muyuan.system.infrastructure.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.redis.manage.RedisCacheManager;
import com.muyuan.system.infrastructure.persistence.dao.SysMenuMapper;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.domain.repo.SysMenuRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @ClassName MenuRepoImpl
 * Description MenuRepoImpl
 * @Author 2456910384
 * @Date 2022/2/11 16:39
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class SysMenuRepoImpl implements SysMenuRepo {

    private SysMenuMapper sysMenuMapper;

    private RedisCacheManager redisCacheManager;

    @Override
    public List<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        List<String> perms = new ArrayList<>();
        Iterator<String> it = roleNames.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleName = it.next();
            if (StrUtil.isNotEmpty(roleName)) {
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
        return sysMenuMapper.selectMenuPermissionByRoleNames(Arrays.asList(roleName));
    }

    @Override
    public List<SysMenu> selectMenuByRoleNames(List<String> roleNames) {
        List<SysMenu> menus = new ArrayList<>();
        Iterator<String> it = roleNames.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleName = it.next();
//            redisCacheManager.redisUtils.del(RedisConst.ROLE_MENU_KEY_PREFIX+roleName);
            String cacheMenuJson = (String) redisCacheManager.get(RedisConst.ROLE_MENU_KEY_PREFIX, roleName,
                    () -> JSONUtil.toJsonString(selectMenuByRoleName(roleName))
            );
            if (StrUtil.isNotEmpty(cacheMenuJson)) {
                menus.addAll(JSONUtil.parseObjectList(cacheMenuJson, ArrayList.class, SysMenu.class));
            }
        }

        // 去重
        return menus.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(SysMenu::getName))), ArrayList::new)
        );
    }

    @Override
    public List<SysMenu> selectMenuByRoleName(String roleName) {
        return sysMenuMapper.selectMenuByRoleNames(Arrays.asList(roleName));
    }

    @Override
    public List<SysMenu> select(Map params) {
        return sysMenuMapper.selectList(params);
    }

    @Override
    public SysMenu selectOne(Map params) {
        return sysMenuMapper.selectOne(params);
    }

    @Override
    public int insert(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }
}
