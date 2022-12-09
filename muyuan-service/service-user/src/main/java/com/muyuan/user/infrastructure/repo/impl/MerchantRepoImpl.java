package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
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

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;
import static com.muyuan.user.infrastructure.repo.mapper.OperatorMapper.PHONE;
import static com.muyuan.user.infrastructure.repo.mapper.OperatorMapper.USERNAME;


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
        SqlBuilder sqlBuilder = new SqlBuilder(OperatorDO.class)
                .like(USERNAME, command.getUsername())
                .eq(STATUS, command.getStatus())
                .eq(PHONE, command.getPhone())
                .orderByDesc(CREATE_TIME);

        Page<Merchant> page = Page.<Merchant>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<MerchantDO> list = mapper.selectList(sqlBuilder.build());

        page.setRows(converter.toMerchants(list));

        return page;
    }

    @Override
    public Merchant selectOneByUsername(Username username, PlatformType platformType) {
        MerchantDO merchantDO = mapper.selectOne(new SqlBuilder(MerchantDO.class)
                .eq(OperatorMapper.USERNAME, username.getValue())
                .eq(OperatorMapper.STATUS, OperatorMapper.STATUS_OK)
                .build());
        Merchant merchant = converter.to(merchantDO);
        if (null != merchantDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(merchantDO.getId(),platformType.getCode());
            merchant.setRoles(converter.toRoles(roleDOS));
        }

        return merchant;
    }

    @Override
    public Merchant selectOneByID(UserID userID, PlatformType platformType) {
        MerchantDO merchantDO = mapper.selectOne(new SqlBuilder(MerchantDO.class)
                .eq(OperatorMapper.ID, userID.getValue())
                .eq(OperatorMapper.STATUS, OperatorMapper.STATUS_OK)
                .build());
        Merchant merchant = converter.to(merchantDO);
        if (null != merchantDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(merchantDO.getId(),platformType.getCode());
            merchant.setRoles(converter.toRoles(roleDOS));
        }

        return merchant;
    }

    @Override
    public Merchant select(Merchant.Identify identify) {
        MerchantDO merchantDO = mapper.selectOne(new SqlBuilder(MerchantDO.class).select(ID)
                .eq(USERNAME, identify.getUsername().getValue())
                .eq(ID, ObjectUtils.isEmpty(identify.getUserID()) ? identify.getUserID() : identify.getUserID().getValue())
                .build());

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
    public void deleteRef(UserID userID) {
        userRoleMapper.deleteBy(new SqlBuilder()
                .eq(USER_ID,userID.getValue())
                .build()) ;
    }

    @Override
    public Page<Merchant> selectAllocatedList(UserQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(MerchantDO.class)
                .like(USERNAME, command.getUsername())
                .eq(PHONE, command.getPhone());

        Page<Merchant> page = Page.<Merchant>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<MerchantDO> list = mapper.selectAllocatedList(command.getRoleId(),sqlBuilder.build());

        page.setRows(converter.toMerchants(list));

        return page;
    }

    @Override
    public Page<Merchant> selectUnallocatedList(UserQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(MerchantDO.class)
                .like(USERNAME, command.getUsername())
                .eq(PHONE, command.getPhone());

        Page<Merchant> page = Page.<Merchant>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<MerchantDO> list = mapper.selectUnallocatedList(command.getRoleId(),sqlBuilder.build());

        page.setRows(converter.toMerchants(list));

        return page;
    }
}
