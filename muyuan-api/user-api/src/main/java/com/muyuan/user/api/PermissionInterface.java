package com.muyuan.user.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.api.dto.PermissionRequest;

import java.util.Set;

/**
 * @ClassName PermissionInterface 接口
 * Description 权限接口
 * @Author 2456910384
 * @Date 2022/9/15 17:32
 * @Version 1.0
 */
public interface PermissionInterface {

    Result<Set<String>> getPermissionByUserID(PermissionQueryRequest request);

    /**
     * 权限列表
     * @param request
     * @return
     */
    Result<Page<PermissionDTO>> list(PermissionQueryRequest request);


    /**
     * 添加权限
     * @param request
     * @return
     */
    Result add(PermissionRequest request);

    /**
     * 权限信息
     * @param id
     * @return
     */
    Result<PermissionDTO> getPermission(Long id);

    /**
     * 更新权限数据
     * @param request
     * @return
     */
    Result updatePermission(PermissionRequest request);

    /**
     *  删除权限数据
     * @param ids
     * @return
     */
    Result deletePermission(Long... ids);
}
