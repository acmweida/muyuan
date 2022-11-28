package com.muyuan.user.domain.service;


import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.face.dto.RoleCommand;
import com.muyuan.user.face.dto.RoleQueryCommand;

import java.util.List;
import java.util.Optional;

/**
 * 角色域服务接口
 */
public interface RoleService {


    /**
     * 通过用户ID 获取角色
     * @param userId
     * @param platformType
     * @return
     */
    List<Role> selectRoleByUserId(UserID userId, PlatformType platformType);


    Page<Role> list(RoleQueryCommand command);


    Optional<Role> getRoleById(Long id);

    /**
     * 唯一性检查
     * @param identify
     * @return
     */
    String checkUnique(Role.Identify identify);

    /**
     * 新增角色信息
     * @param command
     * @return
     */
    boolean addRole(RoleCommand command);

    /**
     * 更新角色信息
     * @param command
     * @return
     */
    boolean updateRole(RoleCommand command);

    /**
     * 删除角色信息
     * @param ids
     * @return
     */
    boolean deleteRoleById(Long... ids);

}
