package com.muyuan.manager.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.manager.system.dto.PermissionQueryParams;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.PermissionRequest;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName PermissionService
 * Description 权限
 * @Author 2456910384
 * @Date 2022/11/16 13:46
 * @Version 1.0
 */
public interface PermissionService {

    /**
     * 查询权限信息
     * @param params
     * @return
     */
    Page<PermissionDTO> list(PermissionQueryParams params);

    /**
     * 权限信息查询
     * @param roleId
     * @return
     */
    List<PermissionDTO> getByRoleId(Long roleId);


    /**
     * 权限添加
     * @param request
     */
    Result add(PermissionRequest request);

    /**
     * 权限信息查询
     * @param id
     * @return
     */
    Optional<PermissionDTO> get(Long id);

    /**
     * 权限信息变更
     * @param request
     * @return
     */
    Result update(PermissionRequest request);

    /**
     * 删除
     * @param ids
     * @return
     */
    Result deleteById(Long... ids);
}
