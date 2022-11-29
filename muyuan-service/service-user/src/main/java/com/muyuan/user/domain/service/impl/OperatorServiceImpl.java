package com.muyuan.user.domain.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.repo.OperatorRepo;
import com.muyuan.user.domain.service.OperatorService;
import com.muyuan.user.face.dto.OperatorQueryCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName OperatorDomainServiceImpl
 * Description
 * @Author 2456910384
 * @Date 2022/9/14 10:07
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private OperatorRepo repo;

    @Override
    public Page<Operator> list(OperatorQueryCommand commend) {
        return repo.select(commend);
    }

    @Override
    public Optional<Operator> getUserByUsername(OperatorQueryCommand command) {
        Username username = new Username(command.getUsername());
        PlatformType platformType = command.getPlatformType();

        Operator operator = repo.selectOneByUsername(username, platformType);

        return Optional.ofNullable(operator);
    }

    @Override
    public Optional<Operator> getUserByyId(UserID userId,PlatformType platformType) {
        return Optional.of(userId)
                .map(id_ -> {
                    return repo.selectOneByID(id_,platformType);
                });
    }
}
