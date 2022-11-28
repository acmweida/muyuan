package com.muyuan.user.domain.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.face.dto.OperatorQueryCommand;

public interface OperatorRepo {

//    Operator selectOne(Operator operator);

    Page<Operator> select(OperatorQueryCommand command);

    Operator selectOneByUsername(Username username, PlatformType platformType);

    Operator selectOneByID(UserID userID, PlatformType platformType);

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
