package com.muyuan.member.domain.query.impl;

import com.muyuan.member.domain.entity.RoleEntity;
import com.muyuan.member.domain.query.MenuQuery;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    @Override
    public Set<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        Set<String> perms = new HashSet<>();
        if (RoleEntity.isShopKeeper(roleNames)) {
            perms.add("*:*:*");
        } else {
            /**
             * TODO:非SHOP_KEEPER使用
             */
            perms.add("*:*:*");
        }
        return perms;
    }
}
