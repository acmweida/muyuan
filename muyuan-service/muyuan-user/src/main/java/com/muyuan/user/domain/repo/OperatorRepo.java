package com.muyuan.user.domain.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.user.domain.model.entity.user.Operator;
import com.muyuan.user.domain.model.valueobject.OperatorUsername;

public interface OperatorRepo extends BaseRepo {



//    Operator selectOne(Operator operator);

    Operator selectOneByUsername(OperatorUsername username);

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
