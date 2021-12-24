package com.muyuan.member.domain.repo;

import com.muyuan.member.domain.model.Role;

import java.util.List;

public interface RoleRepo {

//    Role find(int userNo);
//
//    Role selectOne(Map params);
//
//    void insert(Role dataObject);

    List<Role> selectRoleByUserId(Long userId);
}
