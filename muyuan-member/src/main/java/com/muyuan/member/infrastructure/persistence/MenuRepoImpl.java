package com.muyuan.member.infrastructure.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheManager;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.repo.MenuRepo;
import com.muyuan.member.infrastructure.persistence.dao.MenuMapper;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Component
public class MenuRepoImpl implements MenuRepo {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Override
    public List<String> selectMenuPermissionByRoleCodes(List<String> roleCodes) {
        List<String> perms = new ArrayList<>();
        Iterator<String> it = roleCodes.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleCode = it.next();
            if (StrUtil.isNotEmpty(roleCode)) {
//                redisCacheManager.redisUtils.del(RedisConst.ROLE_PERM_KEY_PREFIX+roleCode);
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
        return menuMapper.selectMenuPermissionByRoleCodes(Arrays.asList(roleCode));
    }

    @Override
    public List<Menu> selectMenuByRoleCodes(List<String> roleCodes) {
        List<Menu> menus = new ArrayList<>();
        Iterator<String> it = roleCodes.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleCode = it.next();
//            redisCacheManager.redisUtils.del(RedisConst.ROLE_MENU_KEY_PREFIX+roleCode);
            String cacheMenuJson = (String) redisCacheManager.get(RedisConst.MEMBER_ROLE_MENU_KEY_PREFIX, roleCode,
                    () -> JSONUtil.toJsonString(selectMenuByRoleCode(roleCode))
            );
            if (StrUtil.isNotEmpty(cacheMenuJson)) {
                menus.addAll(JSONUtil.parseObjectList(cacheMenuJson, ArrayList.class, Menu.class));
            }
        }

        // 去重
        return menus.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(Menu::getName))), ArrayList::new)
        );
    }

    @Override
    public List<Menu> selectMenuByRoleCode(String roleCode) {
        List<Menu> menus = menuMapper.selectMenuByRoleCodes(Arrays.asList(roleCode));
        return menus;
    }

    @Override
    public List<Menu> select(Map params) {
        return menuMapper.selectList(params);
    }

    @Override
    public List<Menu> listByRoleId(String... roleIds) {
        return menuMapper.selectMenuByRoleId(roleIds);
    }

    @Override
    public Menu selectOne(Map params) {
        return menuMapper.selectOne(params);
    }

    @Override
    public void insert(Menu menu) {
        menuMapper.insert((Menu)menu);
    }

    @Override
    public void deleteById(String... id) {
        menuMapper.deleteBy(new SqlBuilder().in("id",id).build());
    }

    @Override
    public void updateById(Menu menu) {
        menuMapper.updateBy(menu,"id");
    }

    @Override
    public void refreshCache() {
        refreshCache(RedisConst.ALL_PLACE_HOLDER);
    }

    @Override
    public void refreshCache(String roleCode) {
        redisCacheManager.delayDoubleDel(RedisConst.MEMBER_ROLE_PERM_KEY_PREFIX+roleCode);
        redisCacheManager.delayDoubleDel(RedisConst.MEMBER_ROLE_MENU_KEY_PREFIX+roleCode);
    }


}
