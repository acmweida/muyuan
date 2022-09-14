package com.muyuan.user.domain.service.impl;

import com.muyuan.user.domain.model.entity.user.Operator;
import com.muyuan.user.domain.model.valueobject.OperatorUsername;
import com.muyuan.user.domain.repo.OperatorRepo;
import com.muyuan.user.domain.service.OperatorDomainService;
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
public class OperatorDomainServiceImpl  implements OperatorDomainService {

    private OperatorRepo repo;

    @Override
    public Optional<Operator> getOperatorByUsername(OperatorQueryCommand command) {
        OperatorUsername username = new OperatorUsername(command.getUsername());

        Operator operator = repo.selectOneByUsername(username);

        return Optional.ofNullable(operator);
    }
}
