package com.muyuan.user.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.SqlParamsBuilder;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.repo.OperatorRepo;
import com.muyuan.user.face.dto.UserQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.dataobject.OperatorDO;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.dataobject.UserRoleDO;
import com.muyuan.user.infrastructure.repo.mapper.OperatorMapper;
import com.muyuan.user.infrastructure.repo.mapper.RoleMapper;
import com.muyuan.user.infrastructure.repo.mapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName OperatorRepoImpl
 * Description
 * @Author 2456910384
 * @Date 2022/9/14 10:28
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class OperatorRepoImpl implements OperatorRepo {

    private UserConverter converter;

    private OperatorMapper mapper;

    private RoleMapper roleMapper;

    private UserRoleMapper userRoleMapper;

    @Override
    public Page<Operator> select(UserQueryCommand command) {
        LambdaQueryWrapper<OperatorDO> wrapper = new LambdaQueryWrapper<OperatorDO>()
                .like(OperatorDO::getUsername, command.getUsername())
                .eq(OperatorDO::getStatus, command.getStatus())
                .eq(OperatorDO::getPhone, command.getPhone())
                .orderByDesc(OperatorDO::getCreateTime);

        Page<Operator> page = Page.<Operator>builder()
                .pageSize(command.getPageSize())
                .pageNum(command.getPageNum())
                .build();

        List<OperatorDO> list = command.enablePage() ?
                mapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                mapper.selectList(wrapper);

        page.setRows(converter.toUsers(list));

        return page;
    }

    @Override
    public Operator selectOneByUsername(Username username, PlatformType platformType) {
        OperatorDO operatorDO = mapper.selectOne(new LambdaQueryWrapper<OperatorDO>()
                .eq(OperatorDO::getUsername, username.getValue())
                .eq(OperatorDO::getStatus, OperatorMapper.STATUS_OK));
        Operator operator = converter.to(operatorDO);
        if (null != operatorDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(operatorDO.getId(), platformType.getCode());
            operator.setRoles(converter.toRoles(roleDOS));
        }

        return operator;
    }

    @Override
    public Operator selectOneByID(UserID userID, PlatformType platformType) {
        OperatorDO operatorDO = mapper.selectOne(new LambdaQueryWrapper<OperatorDO>()
                .eq(OperatorDO::getId, userID.getValue())
                .eq(OperatorDO::getStatus, OperatorMapper.STATUS_OK));
        Operator operator = converter.to(operatorDO);
        if (null != operatorDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(operatorDO.getId(), platformType.getCode());
            operator.setRoles(converter.toRoles(roleDOS));
        }

        return operator;
    }

    @Override
    public Operator selectOperator(Operator.Identify identify) {
        OperatorDO operatorDO = mapper.selectOne(new LambdaQueryWrapper<OperatorDO>()
                .select(OperatorDO::getId)
                .eq(OperatorDO::getUsername, identify.getUsername().getValue())
                .eq(ObjectUtils.isNotEmpty(identify.getUserID()), OperatorDO::getId, identify.getUserID().getValue()));

        return converter.to(operatorDO);
    }

    @Override
    public void insert(Operator operator) {
        OperatorDO to = converter.to(operator);
        mapper.insert(to);
        operator.setId(new UserID(to.getId()));
    }

    @Override
    public boolean insertRef(UserID userID, RoleID... roleIds) {
        if (ObjectUtils.isEmpty(roleIds)) {
            return true;
        }
        List<UserRoleDO> ref = new ArrayList<>();
        for (RoleID roleId : roleIds) {
            ref.add(UserRoleDO.builder()
                    .roleId(roleId.getValue())
                    .userId(userID.getValue())
                    .build());
        }

        return userRoleMapper.batchInsert(ref) > 0;
    }

    @Override
    public void deleteRef(UserID userID) {
        userRoleMapper.deleteById(new LambdaQueryWrapper<UserRoleDO>()
                .eq(UserRoleDO::getUserId, userID.getValue()));
    }

    @Override
    public Page<Operator> selectAllocatedList(UserQueryCommand command) {
        SqlParamsBuilder<OperatorDO> LambdaQueryWrapper = new SqlParamsBuilder<OperatorDO>()
                .param(OperatorDO::getUsername, command.getUsername())
                .param(OperatorDO::getPhone, command.getPhone());

        Page<Operator> page = Page.<Operator>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            LambdaQueryWrapper.page(page);
        }

        List<OperatorDO> list = mapper.selectAllocatedList(command.getRoleId(), LambdaQueryWrapper.build());

        page.setRows(converter.toUsers(list));

        return page;
    }

    @Override
    public Page<Operator> selectUnallocatedList(UserQueryCommand command) {
        SqlParamsBuilder<OperatorDO> LambdaQueryWrapper = new SqlParamsBuilder<OperatorDO>()
                .param(OperatorDO::getUsername, command.getUsername())
                .param(OperatorDO::getPhone, command.getPhone());

        Page<Operator> page = Page.<Operator>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            LambdaQueryWrapper.page(page);
        }

        List<OperatorDO> list = mapper.selectUnallocatedList(command.getRoleId(), LambdaQueryWrapper.build());

        page.setRows(converter.toUsers(list));

        return page;
    }
}
