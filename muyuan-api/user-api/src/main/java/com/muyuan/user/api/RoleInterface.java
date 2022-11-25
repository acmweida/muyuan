package com.muyuan.user.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleQueryRequest;
import com.muyuan.user.api.dto.RoleRequest;


/**
 * @ClassName MenuInterface
 * Description 角色接口
 * @Author 2456910384
 * @Date 2022/10/13 11:06
 * @Version 1.0
 */
public interface RoleInterface {

    /**
     * 角色列表查询
     * @param request
     * @return
     */
    Result<Page<RoleDTO>> list(RoleQueryRequest request);

    Result<RoleDTO> getRole(Long id);

    /**
     * 添加角色
     * @param request
     * @return
     */
    Result add(RoleRequest request);

    /**
     * 更新角色
     * @param request
     * @return
     */
    Result updateRole(RoleRequest request);

    /**
     *  删除角色
     * @param ids
     * @return
     */
    Result deleteRole(Long... ids);

}
