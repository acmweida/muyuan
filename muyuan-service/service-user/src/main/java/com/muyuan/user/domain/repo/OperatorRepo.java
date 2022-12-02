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

    Operator selectOperator(Operator.Identify identify);

    void insert(Operator operator);

    boolean addRef(UserID roleID, Long... roleIds);

    Page<Operator> selectAllocatedList(OperatorQueryCommand command);

    Page<Operator> selectUnallocatedList(OperatorQueryCommand command);

}
