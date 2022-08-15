package com.muyuan.manager.system.domains.service;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.system.domains.model.SysUser;
import com.muyuan.manager.system.domains.dto.SysUserDTO;

import java.util.Optional;

/**
 * @ClassName SysUserDomainService 接口
 * Description 系统用户域服务
 * @Author 2456910384
 * @Date 2022/4/18 11:45
 * @Version 1.0
 */
public interface SysUserDomainService {

    /**
     * 列表查询
     * @param sysUserDTO
     * @return
     */
    Page<SysUser> list(SysUserDTO sysUserDTO);

    /**
     * 列表查询
     * @param sysUserDTO
     * @return
     */
    Page<SysUser> selectAllocatedList(SysUserDTO sysUserDTO);

    /**
     * 列表查询 查询角色没有分配的用户
     * @param sysUserDTO
     * @return
     */
    Page<SysUser> selectUnallocatedList(SysUserDTO sysUserDTO);

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
    Optional<SysUser> getByyId(Long  userId);


    /**
     * 系统用户新增
     * @param sysUserDTO
     * @return
     */
    void add(SysUserDTO sysUserDTO);

    /**
     * 检查唯一性
     * @param sysUser
     * @return
     */
    String checkAccountNameUnique(SysUser sysUser);
}
