package com.muyuan.member.infrastructure.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheManager;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.repo.MenuRepo;
import com.muyuan.member.infrastructure.persistence.dao.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<String> selectMenuPermissionByRoleCodes(List<String> roleCodes) {
        List<String> perms = new ArrayList<>();
        Iterator<String> it = roleCodes.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleCode = it.next();
            if (StrUtil.isNotEmpty(roleCode)) {
                Set<String> rolePerms = redisCacheManager.sGet(RedisConst.MEMBER_ROLE_PERM_KEY_PREFIX, roleCode, () -> new HashSet(selectMenuPermissionByRoleCode(roleCode)));
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
    public void insert(SysMenu sysMenu) {
        sysMenuMapper.insert((SysMenu)sysMenu);
    }

    @Override
    public void deleteById(String... id) {
        sysMenuMapper.deleteBy(new SqlBuilder().in("id",id).build());
    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateBy(sysMenu,"id");
    }

    @Override
    public void refreshCache() {
        refreshCache(RedisConst.ALL_PLACE_HOLDER);
    }

    @Override
    public void refreshCache(String roleCode) {
        redisCacheManager.delayDoubleDel(RedisConst.ROLE_PERM_KEY_PREFIX+roleCode);
        redisCacheManager.delayDoubleDel(RedisConst.ROLE_MENU_KEY_PREFIX+roleCode);
    }


}
