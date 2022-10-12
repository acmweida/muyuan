package com.muyuan.user.domain.service;


import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.user.Role;
import com.muyuan.user.domain.model.valueobject.UserID;

import java.util.List;

/**
 * 角色域服务接口
 */
public interface RoleDomainService {


    /**
     * 通过用户ID 获取角色
     * @param userId
     * @param platformType
     * @return
     */
    List<Role> selectRoleByUserId(UserID userId, PlatformType platformType);

}
