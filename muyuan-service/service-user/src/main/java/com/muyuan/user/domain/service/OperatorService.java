package com.muyuan.user.domain.service;


import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.face.dto.OperatorQueryCommand;

import java.util.Optional;

/**
 * 用户域服务接口
 */
public interface OperatorService {

    /**
     * 角色分页查询
     * @param commend
     * @return
     */
    Page<Operator> list(OperatorQueryCommand commend);

    /**
     * 登录获取用户信息 内部RPC
     * @param command
     * @return
     */
    Optional<Operator> getUserByUsername(OperatorQueryCommand command);


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    Optional<Operator> getUserByyId(UserID userId, PlatformType platformType);

}
