package com.muyuan.member.domain.service.impl;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.member.domain.entity.RoleEntity;
import com.muyuan.member.domain.model.Menu;
import com.muyuan.member.domain.query.MenuQuery;
import com.muyuan.member.domain.repo.MenuRepo;
import com.muyuan.member.domain.service.MenuDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MenuDomainServiceImpl implements MenuDomainService {

    public MenuRepo menuRepo;;

    public MenuQuery menuQuery;

    /**
     * 通过角色名称查询权限
     * @param roleNames
     * @return
     */
    public Set<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        Set<String> perms = new HashSet<>();
        if (RoleEntity.isShopKeeper(roleNames)) {
            perms.add(SecurityConst.ALL_PERMISSION);
        } else {
             perms = menuQuery.selectMenuPermissionByRoleNames(roleNames);
        }
        return perms;
    }



}
