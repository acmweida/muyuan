package com.muyuan.member.domains.repo;

import com.muyuan.member.domains.model.User;

import java.util.List;
import java.util.Map;

public interface UserRepo {

    User find(int userNo);

    User selectOne(Map params);

    boolean insert(User dataObject);

    List<User> selectAllocatedList(Map params);

    void update(User user);
}
