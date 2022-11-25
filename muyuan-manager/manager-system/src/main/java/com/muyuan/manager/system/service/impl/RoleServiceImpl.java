package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.system.dto.RoleQueryParams;
import com.muyuan.manager.system.model.SysRole;
import com.muyuan.manager.system.model.SysUserRole;
import com.muyuan.manager.system.repo.SysRoleRepo;
import com.muyuan.manager.system.service.RoleService;
import com.muyuan.user.api.RoleInterface;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleQueryRequest;
import com.muyuan.user.api.dto.RoleRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName SysRoleDomainServiceImpl
 * Description 角色域服务
 * @Author 2456910384
 * @Date 2022/4/18 15:41
 * @Version 1.0
 */
@Component
@Slf4j
public class RoleServiceImpl implements RoleService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private RoleInterface roleInterface;

    @Autowired
    private SysRoleRepo sysRoleRepo;

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        Assert.notNull(userId, "user Id is null");
        return sysRoleRepo.selectRoleByUserId(userId);
    }

    @Override
    public Page<RoleDTO> list(RoleQueryParams params) {

        RoleQueryRequest request = RoleQueryRequest.builder()
                .name(params.getName())
                .status(params.getStatus())
                .platformType(params.getPlatformType() == null ? PlatformType.OPERATOR : PlatformType.trance(params.getPlatformType()))
                .build();

        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<RoleDTO>> data = roleInterface.list(request);

        return data.getData();
    }

    @Override
    public void selectUser(Long roleId, Long[] userIds) {
        FunctionUtil.getIfNotNullThen(
                () -> userIds,
                (v) -> {
                    List<SysUserRole> set = new ArrayList<>(userIds.length);
                    for (Long userId : userIds) {
                        set.add(SysUserRole.builder().roleId(roleId).userId(userId).build());
                    }
                    sysRoleRepo.batchInsertRole(set);
                }
        );
    }

    @Override
    public Result add(RoleRequest request) {
        return roleInterface.add(request);
    }

    @Override
    public Result update(RoleRequest request) {
        return roleInterface.updateRole(request);
    }

    @Override
    public Optional<RoleDTO> getById(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<RoleDTO> role = roleInterface.getRole(id_);
                    return ResultUtil.getOr(role,null);
                });
    }

    @Override
    public Result deleteById(Long... ids) {
        return roleInterface.deleteRole(ids);
    }
}
