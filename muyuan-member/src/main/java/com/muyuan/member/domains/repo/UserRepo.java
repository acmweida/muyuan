package com.muyuan.member.domains.repo;

import com.muyuan.member.domains.model.User;
import com.muyuan.member.domains.model.UserRole;

import java.util.List;
import java.util.Map;

public interface UserRepo {

    User selectOne(Map params);

    void insert(User dataObject);

    List<User> selectAllocatedList(Map params);

    void update(User user);

    void insert(UserRole userRole);
}
