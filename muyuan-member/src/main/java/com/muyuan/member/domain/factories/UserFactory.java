package com.muyuan.member.domain.factories;

import com.muyuan.common.spring.ApplicationContextHandler;
import com.muyuan.member.domain.entity.UserEntity;
import com.muyuan.member.domain.repo.UserRepo;

public class UserFactory {

    /**
     * UserEntity
     * @return
     */
    public static UserEntity create() {
        return new UserEntity(ApplicationContextHandler.getContext().getBean(UserRepo.class));
    }
}
