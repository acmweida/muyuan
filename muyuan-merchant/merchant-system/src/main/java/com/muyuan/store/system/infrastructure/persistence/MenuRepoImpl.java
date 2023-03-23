package com.muyuan.store.system.infrastructure.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.store.system.domains.dto.MenuDTO;
import com.muyuan.store.system.domains.model.Menu;
import com.muyuan.store.system.domains.repo.MenuRepo;
import com.muyuan.store.system.infrastructure.persistence.mapper.MenuMapper;
import com.muyuan.store.system.infrastructure.persistence.mapper.RoleMenuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    private MenuMapper menuMapper;

    private RoleMenuMapper roleMenuMapper;

    private RedisCacheService redisCacheService;

    @Override
    public List<String> selectMenuPermissionByRoleCodes(List<String> roleCodes) {
        List<String> perms = new ArrayList<>();
        Iterator<String> it = roleCodes.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleCode = it.next();
            if (StrUtil.isNotEmpty(roleCode)) {
//                redisCacheManager.redisUtils.del(RedisConst.ROLE_PERM_KEY_PREFIX+roleCode);
                Set<String> rolePerms = CacheServiceUtil.sGetAndUpdate(redisCacheService,
                        RedisConst.MEMBER_ROLE_PERM_KEY_PREFIX+roleCode,
                        () -> new HashSet(selectMenuPermissionByRoleCode(roleCode)),
                        String.class
                );
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
            String cacheMenuJson = CacheServiceUtil.getAndUpdate(redisCacheService,RedisConst.MEMBER_ROLE_MENU_KEY_PREFIX+roleCode,
                    () -> JSONUtil.toJsonString(selectMenuByRoleCode(roleCode))
            );
            if (StrUtil.isNotEmpty(cacheMenuJson)) {
                menus.addAll(JSONUtil.parseObjectList(cacheMenuJson, ArrayList.class, Menu.class));
            }
        }

        // 去重
        return menus.stream().sorted(
                (item1,item2 ) -> item1.getOrderNum() - item2.getOrderNum()
        ).collect(
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
    public List<Menu> select(MenuDTO menuDTO) {
        return menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getName, menuDTO.getName())
                .eq(Menu::getStatus, menuDTO.getStatus())
                .orderByAsc(Menu::getOrderNum));
    }

    @Override
    public List<Menu> listByRoleId(String... roleIds) {
        return menuMapper.selectMenuByRoleId(roleIds);
    }

    @Override
    public Menu selectOne(Menu menu) {
        return menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getName, menu.getName())
                .eq(Menu::getParentId, menu.getParentId())
                .eq(Menu::getType, menu.getType())
                .eq(Menu::getStatus, STATUS_OK)
                .eq(Menu::getId, menu.getId()));
    }

    @Override
    public void insert(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    @Transactional
    public void deleteById(String... id) {
        menuMapper.deleteBatchIds(Arrays.asList(id));
        // 清除无父菜单的子菜单
        while (menuMapper.delete() > 0) {
        }
        roleMenuMapper.delete();
    }

    @Override
    public void updateById(Menu menu) {
        menuMapper.updateById(menu);
    }

    @Override
    public void refreshCache() {
        refreshCache(RedisConst.ALL_PLACE_HOLDER);
    }

    @Override
    public void refreshCache(String roleCode) {
        redisCacheService.delayDoubleDel(RedisConst.MEMBER_ROLE_PERM_KEY_PREFIX+roleCode);
        redisCacheService.delayDoubleDel(RedisConst.MEMBER_ROLE_MENU_KEY_PREFIX+roleCode);
    }

}
