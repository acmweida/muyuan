package com.muyuan.manager.system.service;

import com.muyuan.common.bean.Page;
import com.muyuan.manager.system.dto.OperatorParams;
import com.muyuan.manager.system.dto.OperatorQueryParams;
import com.muyuan.manager.system.model.SysUser;
import com.muyuan.user.api.dto.OperatorDTO;

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
     * @param operatorQueryParams
     * @return
     */
    Page<SysUser> selectAllocatedList(OperatorQueryParams operatorQueryParams);

    /**
     * 列表查询 查询角色没有分配的用户
     * @param operatorQueryParams
     * @return
     */
    Page<SysUser> selectUnallocatedList(OperatorQueryParams operatorQueryParams);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    Optional<SysUser> getByyUsername(String  username);


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    Optional<OperatorDTO> get(Long  userId);


    /**
     * 系统用户新增
     * @param operatorParams
     * @return
     */
    void add(OperatorParams operatorParams);

    /**
     * 检查唯一性
     * @param sysUser
     * @return
     */
    String checkAccountNameUnique(SysUser sysUser);
}
