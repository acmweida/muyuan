package com.muyuan.user.api;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;

/**
 * @ClassName OperatorInterface 接口
 * Description 用户信息接口
 * @Author 2456910384
 * @Date 2022/9/13 16:20
 * @Version 1.0
 */
public interface OperatorInterface {

    /**
     * 通过用户名称 获取用户信息
     * 默认用户类型 MEMBER
     * @param request
     * @return
     */
    Result<OperatorDTO> getUserByUsername(OperatorQueryRequest request);

    /**
     * 角色列表
     * @param request
     * @return
     */
    Result<Page<OperatorDTO>> list(OperatorQueryRequest request);

    /**
     * 用户基本信息
     * @param id
     * @return
     */
    Result<OperatorDTO> get(Long id);

}
