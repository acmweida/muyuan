package com.muyuan.user.domain.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.user.domain.model.entity.user.User;
import com.muyuan.user.domain.model.entity.user.UserRole;

import java.util.List;
import java.util.Map;

public interface UserRepo extends BaseRepo {

    String PHONE = "phone";

    String SHOP_ID = "shopId";

    String USERNAME = "username";

    int STATUS_OK = 0;

    User selectOne(User user);

    void insert(User dataObject);

    List<User> selectAllocatedList(Map params);

    void update(User user);

    void insert(UserRole userRole);

    void update(User brand, String... column);
}
