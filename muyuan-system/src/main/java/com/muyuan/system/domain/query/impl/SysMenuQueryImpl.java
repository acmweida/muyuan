package com.muyuan.system.domain.query.impl;

import com.muyuan.system.domain.entity.SysRoleEntity;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.domain.query.SysMenuQuery;
import com.muyuan.system.domain.repo.SysMenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName MenuQueryImpl
 * Description 权限查询
 * @Author 2456910384
 * @Date 2022/2/9 16:52
 * @Version 1.0
 */
@Service
public class SysMenuQueryImpl implements SysMenuQuery {

    @Autowired
    SysMenuRepo sysMenuRepo;

    @Override
    public Set<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        Set<String> perms = new HashSet<>();
        if (SysRoleEntity.isShopKeeper(roleNames)) {
            perms.add("*:*:*");
        } else {
            List<String> permList = sysMenuRepo.selectMenuPermissionByRoleNames(roleNames);
            for (Iterator<String> iterator = permList.iterator();iterator.hasNext();) {
                perms.add(iterator.next());
            }
        }
        return perms;
    }

    @Override
    public List<SysMenu> selectMenuByRoleNames(List<String> roleNames) {
        return sysMenuRepo.selectMenuByRoleNames(roleNames);
    }

}
