package com.muyuan.system.infrastructure.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.thread.CommonThreadPool;
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
    public List<String> selectMenuPermissionByRoleCodes(List<String> roleCodes) {
        List<String> perms = new ArrayList<>();
        Iterator<String> it = roleCodes.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleCode = it.next();
            if (StrUtil.isNotEmpty(roleCode)) {
                Set<String> rolePerms = redisCacheManager.sGet(RedisConst.ROLE_PERM_KEY_PREFIX, roleCode, () -> new HashSet(selectMenuPermissionByRoleCode(roleCode)));
                if (null != rolePerms) {
                    perms.addAll(rolePerms);
                }
            }
        }

        return perms;
    }

    @Override
    public List<String> selectMenuPermissionByRoleCode(String roleCode) {
        return sysMenuMapper.selectMenuPermissionByRoleNames(Arrays.asList(roleCode));
    }

    @Override
    public List<SysMenu> selectMenuByRoleCodes(List<String> roleCodes) {
        List<SysMenu> menus = new ArrayList<>();
        Iterator<String> it = roleCodes.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleCode = it.next();
//            redisCacheManager.redisUtils.del(RedisConst.ROLE_MENU_KEY_PREFIX+roleName);
            String cacheMenuJson = (String) redisCacheManager.get(RedisConst.ROLE_MENU_KEY_PREFIX, roleCode,
                    () -> JSONUtil.toJsonString(selectMenuByRoleCode(roleCode))
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
    public List<SysMenu> selectMenuByRoleCode(String roleCode) {
        List<SysMenu> sysMenus = sysMenuMapper.selectMenuByRoleNames(Arrays.asList(roleCode));
        return sysMenus;
    }

    @Override
    public List<SysMenu> select(Map params) {
        return sysMenuMapper.selectList(params);
    }

    @Override
    public List<SysMenu> listByRoleId(String... roleIds) {
        return sysMenuMapper.selectMenuByRoleId(roleIds);
    }

    @Override
    public SysMenu selectOne(Map params) {
        return sysMenuMapper.selectOne(params);
    }

    @Override
    public int insert(SysMenu sysMenu) {
        return sysMenuMapper.insert((SysMenu)sysMenu);
    }

    @Override
    public int deleteById(String... id) {
        return sysMenuMapper.deleteByIds(id);
    }

    @Override
    public int updateById(SysMenu sysMenu) {
        return sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void refreshCache() {
        Runnable task = () -> {
            redisCacheManager.del(RedisConst.ROLE_PERM_KEY_PREFIX+RedisConst.ALL_PLACE_HOLDER);
            redisCacheManager.del(RedisConst.ROLE_MENU_KEY_PREFIX+RedisConst.ALL_PLACE_HOLDER);
        };
        task.run();
        CommonThreadPool.schedule(task,5);
    }

    @Override
    public void refreshCache(String roleCode) {
        Runnable task = () -> {
            redisCacheManager.del(RedisConst.ROLE_PERM_KEY_PREFIX+roleCode);
            redisCacheManager.del(RedisConst.ROLE_MENU_KEY_PREFIX+roleCode);
        };
        task.run();
        CommonThreadPool.schedule(task,5);
    }
}
