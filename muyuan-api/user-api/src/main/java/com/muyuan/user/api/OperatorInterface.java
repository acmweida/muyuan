package com.muyuan.user.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.UserQueryRequest;
import com.muyuan.user.api.dto.OperatorRequest;

/**
 * @ClassName OperatorInterface 接口
 * Description 运营人员信息接口
 * @Author 2456910384
 * @Date 2022/9/13 16:20
 * @Version 1.0
 */
public interface OperatorInterface {

    /**
     * 角色列表
     * @param request
     * @return
     */
    Result<Page<OperatorDTO>> list(UserQueryRequest request);

    /**
     * 用户基本信息
     * @param id
     * @return
     */
    Result<OperatorDTO> get(Long id);


    /**
     * 添加权限
     * @param request
     * @return
     */
    Result add(OperatorRequest request);

    /**
     * 获取角色管联的用户
     * @param request
     * @return
     */
    Result<Page<OperatorDTO>> selectAllocatedList(UserQueryRequest request);

    /**
     * 获取角色未关联用户
     * @param request
     * @return
     */
    Result<Page<OperatorDTO>> selectUnallocatedList(UserQueryRequest request);


    /**
     * 关联角色
     * @param userId
     * @param roleIds
     * @return
     */
    Result authRole(Long userId,Long[] roleIds);

}
