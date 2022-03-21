package com.muyuan.system.application.query.impl;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.system.domain.entity.SysRoleEntity;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.application.query.SysMenuQuery;
import com.muyuan.system.domain.repo.SysMenuRepo;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SysMenuQueryImpl implements SysMenuQuery {

    SysMenuRepo sysMenuRepo;

    @Override
    public Set<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        Set<String> perms = new HashSet<>();
        if (SysRoleEntity.isAdmin(roleNames)) {
            perms.add(SecurityConst.ALL_PERMISSION);
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
