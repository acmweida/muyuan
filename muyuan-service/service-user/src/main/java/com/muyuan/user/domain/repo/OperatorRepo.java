package com.muyuan.user.domain.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.face.dto.UserQueryCommand;

public interface OperatorRepo {

//    Operator selectOne(Operator operator);

    Page<Operator> select(UserQueryCommand command);

    Operator selectOneByUsername(Username username, PlatformType platformType);

    Operator selectOneByID(UserID userID, PlatformType platformType);

    Operator selectOperator(Operator.Identify identify);

    void insert(Operator operator);

    boolean insertRef(UserID roleID, RoleID... roleIds);

    void deleteRef(UserID userID);

    Page<Operator> selectAllocatedList(UserQueryCommand command);

    Page<Operator> selectUnallocatedList(UserQueryCommand command);

}
