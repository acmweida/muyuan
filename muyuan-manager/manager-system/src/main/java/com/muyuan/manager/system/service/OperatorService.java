package com.muyuan.manager.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.manager.system.dto.OperatorQueryParams;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorRequest;

import java.util.Optional;

/**
 * @ClassName SysUserDomainService 接口
 * Description 系统用户域服务
 * @Author 2456910384
 * @Date 2022/4/18 11:45
 * @Version 1.0
 */
public interface OperatorService {

    /**
     * 列表查询
     * @param params
     * @return
     */
    Page<OperatorDTO> list(OperatorQueryParams params);

    /**
     * 列表查询
     * @param params
     * @return
     */
    Page<OperatorDTO> selectAllocatedList(OperatorQueryParams params);

    /**
     * 列表查询 查询角色没有分配的用户
     * @param params
     * @return
     */
    Page<OperatorDTO> selectUnallocatedList(OperatorQueryParams params);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    Optional<OperatorDTO> get(Long  userId);


    /**
     * 系统用户新增
     * @param request
     * @return
     */
    Result add(OperatorRequest request);

    /**
     * 关联角色
     * @param userId
     * @param roleIds
     * @return
     */
    Result authRole(Long userId,Long[] roleIds);

}
