package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.repo.OperatorRepo;
import com.muyuan.user.face.dto.OperatorQueryCommand;
import com.muyuan.user.infrastructure.repo.converter.UserConverter;
import com.muyuan.user.infrastructure.repo.dataobject.RoleDO;
import com.muyuan.user.infrastructure.repo.dataobject.OperatorDO;
import com.muyuan.user.infrastructure.repo.mapper.RoleMapper;
import com.muyuan.user.infrastructure.repo.mapper.OperatorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.CREATE_TIME;
import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.STATUS;
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
public class OperatorRepoImpl implements OperatorRepo {

    private UserConverter converter;

    private OperatorMapper mapper;
    
    private RoleMapper roleMapper;

    @Override
    public Page<Operator> select(OperatorQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(OperatorDO.class)
                .like(USERNAME, command.getUsername())
                .eq(STATUS, command.getStatus())
                .eq(PHONE, command.getPhone())
                .orderByDesc(CREATE_TIME);

        Page<Operator> page = Page.<Operator>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<OperatorDO> list = mapper.selectList(sqlBuilder.build());

        page.setRows(converter.toUsers(list));

        return page;
    }

    @Override
    public Operator selectOneByUsername(Username username, PlatformType platformType) {
        OperatorDO operatorDO = mapper.selectOne(new SqlBuilder(OperatorDO.class)
                .eq(OperatorMapper.USERNAME, username.getValue())
                .eq(OperatorMapper.STATUS, OperatorMapper.STATUS_OK)
                .build());
        Operator operator = converter.to(operatorDO);
        if (null != operatorDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(operatorDO.getId(),platformType.getCode());
            operator.setRoles(converter.toRoles(roleDOS));
        }

        return operator;
    }

    @Override
    public Operator selectOneByID(UserID userID, PlatformType platformType) {
        OperatorDO operatorDO = mapper.selectOne(new SqlBuilder(OperatorDO.class)
                .eq(OperatorMapper.ID, userID.getValue())
                .eq(OperatorMapper.STATUS, OperatorMapper.STATUS_OK)
                .build());
        Operator operator = converter.to(operatorDO);
        if (null != operatorDO) {
            List<RoleDO> roleDOS = roleMapper.selectRoleByUserId(operatorDO.getId(),platformType.getCode());
            operator.setRoles(converter.toRoles(roleDOS));
        }

        return operator;
    }
}
