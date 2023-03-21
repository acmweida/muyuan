package com.muyuan.user.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.SqlParamsBuilder;
import com.muyuan.user.domain.model.entity.Merchant;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.repo.MerchantRepo;
import com.muyuan.user.face.dto.UserQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.dataobject.MerchantDO;
import com.muyuan.user.infrastructure.repo.dataobject.OperatorDO;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.dataobject.UserRoleDO;
import com.muyuan.user.infrastructure.repo.mapper.MerchantMapper;
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
public class MerchantRepoImpl implements MerchantRepo {

    private UserConverter converter;

    private MerchantMapper mapper;

    private RoleMapper roleMapper;

    private UserRoleMapper userRoleMapper;

    @Override
    public Page<Merchant> select(UserQueryCommand command) {
        LambdaQueryWrapper<MerchantDO> wrapper = new LambdaQueryWrapper<MerchantDO>()
                .like(MerchantDO::getUsername, command.getUsername())
                .eq(MerchantDO::getStatus, command.getStatus())
                .eq(MerchantDO::getPhone, command.getPhone())
                .orderByDesc(MerchantDO::getCreateTime);

        Page<Merchant> page = Page.<Merchant>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<MerchantDO> list;
        if (command.enablePage()) {
            IPage<MerchantDO> page1 = mapper.selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                    command.getPageNum(), command.getPageSize()
            ), wrapper);
            list = page1.getRecords();
            page.setTotal((int) page1.getTotal());
        } else {
            list = mapper.selectList(wrapper);
        }

        page.setRows(converter.toMerchants(list));

        return page;
    }

    @Override
    public Merchant selectOneByUsername(Username username, PlatformType platformType) {
        MerchantDO merchantDO = mapper.selectOne(new LambdaQueryWrapper<MerchantDO>()
                .eq(MerchantDO::getUsername, username.getValue())
                .eq(MerchantDO::getStatus, OperatorMapper.STATUS_OK));
        Merchant merchant = converter.to(merchantDO);
        if (null != merchantDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(merchantDO.getId(), platformType.getCode());
            merchant.setRoles(converter.toRoles(roleDOS));
        }

        return merchant;
    }

    @Override
    public Merchant selectOneByID(UserID userID, PlatformType platformType) {
        MerchantDO merchantDO = mapper.selectOne(new LambdaQueryWrapper<MerchantDO>()
                .eq(MerchantDO::getId, userID.getValue())
                .eq(MerchantDO::getStatus, OperatorMapper.STATUS_OK));
        Merchant merchant = converter.to(merchantDO);
        if (null != merchantDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(merchantDO.getId(), platformType.getCode());
            merchant.setRoles(converter.toRoles(roleDOS));
        }

        return merchant;
    }

    @Override
    public Merchant select(Merchant.Identify identify) {
        MerchantDO merchantDO = mapper.selectOne(new LambdaQueryWrapper<MerchantDO>().select(MerchantDO::getId)
                .eq(MerchantDO::getUsername, identify.getUsername().getValue())
                .eq(ObjectUtils.isNotEmpty(identify.getUserID()), MerchantDO::getId, ObjectUtils.isEmpty(identify.getUserID()) ? identify.getUserID() : identify.getUserID().getValue()));

        return converter.to(merchantDO);
    }

    @Override
    public void insert(Merchant merchant) {
        MerchantDO to = converter.to(merchant);
        mapper.insert(to);
        merchant.setId(new UserID(to.getId()));
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
    public int deleteRef(UserID userID) {
        return userRoleMapper.deleteById(userID.getValue());
    }




    @Override
    public Page<Merchant> selectAllocatedList(UserQueryCommand command) {
        SqlParamsBuilder<OperatorDO> sqlBuilder = new SqlParamsBuilder<OperatorDO>()
                .param(OperatorDO::getUsername, command.getUsername())
                .param(OperatorDO::getPhone, command.getPhone());

        Page<Merchant> page = Page.<Merchant>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<MerchantDO> list = mapper.selectAllocatedList(command.getRoleId(), sqlBuilder.build());

        page.setRows(converter.toMerchants(list));

        return page;
    }

    @Override
    public Page<Merchant> selectUnallocatedList(UserQueryCommand command) {
        SqlParamsBuilder<MerchantDO> sqlBuilder = new SqlParamsBuilder<MerchantDO>()
                .param(MerchantDO::getUsername, command.getUsername())
                .param(MerchantDO::getPhone, command.getPhone());

        Page<Merchant> page = Page.<Merchant>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<MerchantDO> list = mapper.selectUnallocatedList(command.getRoleId(), sqlBuilder.build());

        page.setRows(converter.toMerchants(list));

        return page;
    }
}
