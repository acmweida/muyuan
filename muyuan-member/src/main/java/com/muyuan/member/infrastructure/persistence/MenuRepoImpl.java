package com.muyuan.member.infrastructure.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.member.domains.dto.MenuDTO;
import com.muyuan.member.domains.model.Menu;
import com.muyuan.member.domains.repo.MenuRepo;
import com.muyuan.member.infrastructure.persistence.mapper.MenuMapper;
import com.muyuan.member.infrastructure.persistence.mapper.RoleMenuMapper;
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
                Set<String> rolePerms = (Set<String>) redisCacheService.sGetAndUpdate(RedisConst.MEMBER_ROLE_PERM_KEY_PREFIX+roleCode, () -> new HashSet(selectMenuPermissionByRoleCode(roleCode)));
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
            String cacheMenuJson = (String) redisCacheService.getAndUpdate(RedisConst.MEMBER_ROLE_MENU_KEY_PREFIX+roleCode,
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
        return menuMapper.selectList(new SqlBuilder(Menu.class)
                .eq(NAME, menuDTO.getName())
                .eq(STATUS, menuDTO.getStatus())
                .orderByAsc(ORDER_NUM).build());
    }

    @Override
    public List<Menu> listByRoleId(String... roleIds) {
        return menuMapper.selectMenuByRoleId(roleIds);
    }

    @Override
    public Menu selectOne(Menu menu) {
        return menuMapper.selectOne(new SqlBuilder(Menu.class)
                .eq(NAME, menu.getName())
                .eq(PARENT_ID, menu.getParentId())
                .eq(TYPE, menu.getType())
                .eq(STATUS, STATUS_OK)
                .eq(ID, menu.getId())
                .build());
    }

    @Override
    public void insert(Menu menu) {
        menuMapper.insert((Menu)menu);
    }

    @Override
    @Transactional
    public void deleteById(String... id) {
        menuMapper.deleteBy(new SqlBuilder().in(ID,id).build());
        // 清除无父菜单的子菜单
        while (menuMapper.delete() > 0) {
        }
        roleMenuMapper.delete();
    }

    @Override
    public void updateById(Menu menu) {
        menuMapper.updateBy(menu, ID);
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
