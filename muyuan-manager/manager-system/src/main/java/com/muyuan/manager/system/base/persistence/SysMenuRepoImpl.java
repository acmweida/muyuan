package com.muyuan.manager.system.base.persistence;

import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.manager.system.domains.model.SysMenu;
import com.muyuan.manager.system.domains.model.SysRoleMenu;
import com.muyuan.manager.system.domains.repo.SysMenuRepo;
import com.muyuan.manager.system.mapper.SysMenuMapper;
import com.muyuan.manager.system.mapper.SysRoleMenuMapper;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SysMenuRepoImpl implements SysMenuRepo {

    private SysMenuMapper sysMenuMapper;

    private SysRoleMenuMapper sysRoleMenuMapper;

    private RedisCacheService redisCacheService;

    @Override
    public List<String> selectMenuPermissionByRoleCodes(List<String> roleCodes) {
        List<String> perms = new ArrayList<>();
        Iterator<String> it = roleCodes.iterator();

        // 查询缓存
        while (it.hasNext()) {
            String roleCode = it.next();
            if (StrUtil.isNotEmpty(roleCode)) {

                Set<String> rolePerms =  CacheServiceUtil.sGetAndUpdate(redisCacheService,RedisConst.SYS_ROLE_PERM_KEY_PREFIX + roleCode,
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
        return sysMenuMapper.selectMenuPermissionByRoleCodes(Arrays.asList(roleCode));
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
        sysMenuMapper.insertAuto(sysMenu);
        // 默认管理员权限
        sysRoleMenuMapper.insert(new SysRoleMenu(1L, sysMenu.getId()));
    }

    @Override
    public void deleteById(String... id) {
        sysMenuMapper.deleteBy(new SqlBuilder().in("id", id).build());
    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateBy(sysMenu, "id");
    }

    @Override
    public void refreshCache() {
        refreshCache(RedisConst.ALL_PLACE_HOLDER);
    }

    @Override
    public void refreshCache(String roleCode) {
        redisCacheService.delayDoubleDel(RedisConst.SYS_ROLE_PERM_KEY_PREFIX + roleCode);
        redisCacheService.delayDoubleDel(RedisConst.OPERATOR_ROLE_MENU_KEY_PREFIX + roleCode);
    }
}
