package com.muyuan.user.domain.service;


import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.face.dto.RoleQueryCommand;

import java.util.List;
import java.util.Optional;

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


    Page<Role> list(RoleQueryCommand command);


    Optional<Role> getRoleById(Long id);

}
