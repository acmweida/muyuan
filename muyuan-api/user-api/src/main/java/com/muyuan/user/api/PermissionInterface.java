package com.muyuan.user.api;

import com.muyuan.user.api.dto.PermissionQueryRequest;

import java.util.Set;

/**
 * @ClassName PermissionInterface 接口
 * Description 权限接口
 * @Author 2456910384
 * @Date 2022/9/15 17:32
 * @Version 1.0
 */
public interface PermissionInterface {

    Set<String> getPermissionByRoleCodes(PermissionQueryRequest request);
}
