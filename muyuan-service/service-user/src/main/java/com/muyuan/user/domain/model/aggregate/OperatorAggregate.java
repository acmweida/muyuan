package com.muyuan.user.domain.model.aggregate;

import com.muyuan.common.core.domains.mode.aggregate.Aggregate;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.UserID;

/**
 * @ClassName OperatorAggregate
 * Description TODO
 * @Author 2456910384
 * @Date 2022/12/9 10:48
 * @Version 1.0
 */
public class OperatorAggregate implements Aggregate<UserID> {

    private UserID userID;

    private Operator operator;

    @Override
    public UserID getId() {
        return userID;
    }
}
