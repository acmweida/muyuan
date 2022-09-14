package com.muyuan.user.infrastructure.repo.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.user.domain.model.entity.user.Operator;
import com.muyuan.user.domain.model.valueobject.OperatorUsername;
import com.muyuan.user.domain.repo.OperatorRepo;
import com.muyuan.user.infrastructure.repo.converter.OperatorConverter;
import com.muyuan.user.infrastructure.repo.converter.OperatorConverterImpl;
import com.muyuan.user.infrastructure.repo.dataobject.OperatorDO;
import com.muyuan.user.infrastructure.repo.mapper.OperatorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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

    private static final OperatorConverter converter = new OperatorConverterImpl();

    private OperatorMapper mapper;

    @Override
    public Operator selectOneByUsername(OperatorUsername username) {
        OperatorDO operatorDO = mapper.selectOne(new SqlBuilder(OperatorDO.class)
                .eq(OperatorMapper.USERNAME, username.getValue())
                .eq(OperatorMapper.STATUS,OperatorMapper.STATUS_OK)
                .build());
        return converter.to(operatorDO);
    }
}
