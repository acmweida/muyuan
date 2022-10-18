package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.system.domains.factories.SysRoleFactory;
import com.muyuan.manager.system.domains.model.SysRole;
import com.muyuan.manager.system.domains.model.SysRoleMenu;
import com.muyuan.manager.system.domains.model.SysUserRole;
import com.muyuan.manager.system.domains.repo.SysMenuRepo;
import com.muyuan.manager.system.domains.repo.SysRoleRepo;
import com.muyuan.manager.system.dto.RoleQueryParams;
import com.muyuan.manager.system.dto.SysRoleDTO;
import com.muyuan.manager.system.service.RoleDomainService;
import com.muyuan.user.api.RoleInterface;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleQueryRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
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
public class RoleDomainServiceImpl implements RoleDomainService {


    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private RoleInterface roleInterface;

    @Autowired
    private SysRoleRepo sysRoleRepo;

    @Autowired
    private SysMenuRepo sysMenuRepo;

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

        Result<Page<RoleDTO>> data = roleInterface.list(RoleQueryRequest.builder()
                .name(params.getName())
                .status(params.getStatus())
                .platformType(params.getPlatformType() == null ? PlatformType.OPERATOR : PlatformType.trance(params.getPlatformType()))
                .build());

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
    public String checkRoleCodeUnique(SysRole sysRole) {
        Long id = null == sysRole.getId() ? 0 : sysRole.getId();
        sysRole = sysRoleRepo.selectOne(new SqlBuilder(SysRole.class).select("id")
                .eq("code", sysRole.getCode())
                .build());
        if (null != sysRole && !id.equals(sysRole.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRoleDTO sysRoleDTO) {

        SysRole sysRole = SysRoleFactory.newInstance(sysRoleDTO);
        sysRoleRepo.insert(sysRole);

        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(sysRoleDTO.getMenuIds())) {
            Arrays.stream(sysRoleDTO.getMenuIds()).forEach(
                    item -> {
                        sysRoleMenus.add(new SysRoleMenu(sysRole.getId(), Long.valueOf(item)));
                    }
            );
        }
        sysRoleRepo.batchInsert(sysRoleMenus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleDTO sysRoleDTO) {
        SysRole sysRole = sysRoleDTO.convert();
        sysRole.update();
        sysRoleRepo.updateById(sysRole);

        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(sysRoleDTO.getMenuIds())) {
            Arrays.stream(sysRoleDTO.getMenuIds()).forEach(
                    item -> {
                        sysRoleMenus.add(new SysRoleMenu(sysRole.getId(), Long.valueOf(item)));
                    }
            );
        }

        sysRoleRepo.deleteMenuByRoleId(sysRole.getId());

        sysRoleRepo.batchInsert(sysRoleMenus);

        sysMenuRepo.refreshCache(sysRole.getCode());

    }


    @Override
    public Optional<SysRole> getById(String id) {
        SqlBuilder sqlBuilder = new SqlBuilder(SysRole.class)
                .eq("id", id);

        SysRole sysRole = sysRoleRepo.selectOne(sqlBuilder.build());

        if (null == sysRole) {
            return Optional.empty();
        }
        return Optional.of(sysRole);
    }

    @Override
    public void deleteById(String... id) {
        sysRoleRepo.deleteById(id);
    }
}
