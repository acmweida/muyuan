package com.muyuan.user.domain.service.impl;

import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.user.domain.model.entity.User;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.repo.UserRepo;
import com.muyuan.user.domain.service.UserDomainService;
import com.muyuan.user.face.dto.UserQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
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
public class UserDomainServiceImpl implements UserDomainService {

    private UserRepo repo;

    @Override
    public Optional<User> getUserByUsername(UserQueryCommand command) {
        Username username = new Username(command.getUsername());
        PlatformType platformType = command.getPlatformType();

        User user = repo.selectOneByUsername(username, platformType);

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByyId(UserID userId, PlatformType platformType) {
        if (ObjectUtils.isEmpty(platformType)) {
            return Optional.empty();
        }

        return Optional.ofNullable(repo.selectOneByID(userId, platformType));
    }
}
