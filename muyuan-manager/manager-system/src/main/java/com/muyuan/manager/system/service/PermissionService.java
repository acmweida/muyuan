package com.muyuan.manager.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.manager.system.dto.PermissionParams;
import com.muyuan.manager.system.dto.PermissionQueryParams;
import com.muyuan.user.api.dto.PermissionDTO;

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
     * 权限添加
     * @param params
     */
    Result add(PermissionParams params);
}
