package com.muyuan.user.domain.repo;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.user.User;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;

public interface UserRepo {

//    Operator selectOne(Operator operator);

    User selectOneByUsername(Username username, PlatformType platformType);

    User selectOneByID(UserID userID, PlatformType platformType);

//    void insert(Operator operator);
//
//    List<Operator> selectAllocatedList(Map params);
//
//    void update(Operator operator);
//
//    void insert(UserRole userRole);
//
//    void update(Operator brand, String... column);
}
