package com.muyuan.user.domain.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Merchant;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.face.dto.UserQueryCommand;

public interface MerchantRepo {

//    Operator selectOne(Operator operator);

    Page<Merchant> select(UserQueryCommand command);

    Merchant selectOneByUsername(Username username, PlatformType platformType);

    Merchant selectOneByID(UserID userID, PlatformType platformType);

    Merchant select(Merchant.Identify identify);

    void insert(Merchant operator);

    boolean insertRef(UserID roleID, RoleID... roleIds);

    int deleteRef(UserID userID);

    Page<Merchant> selectAllocatedList(UserQueryCommand command);

    Page<Merchant> selectUnallocatedList(UserQueryCommand command);

}
