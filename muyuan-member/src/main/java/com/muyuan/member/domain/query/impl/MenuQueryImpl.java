package com.muyuan.member.domain.query.impl;

import com.muyuan.member.domain.entity.RoleEntity;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.query.MenuQuery;
import com.muyuan.member.domain.repo.MenuRepo;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class MenuQueryImpl implements MenuQuery {

    @Autowired
    MenuRepo menuRepo;

    @Override
    public Set<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        Set<String> perms = new HashSet<>();
        if (RoleEntity.isShopKeeper(roleNames)) {
            perms.add("*:*:*");
        } else {
            List<String> permList = menuRepo.selectMenuPermissionByRoleNames(roleNames);
            for (Iterator<String> iterator = permList.iterator();iterator.hasNext();) {
                perms.add(iterator.next());
            }
        }
        return perms;
    }

    @Override
    public List<Menu> selectMenuByRoleNames(List<String> roleNames) {
        return menuRepo.selectMenuByRoleNames(roleNames);
    }
}
