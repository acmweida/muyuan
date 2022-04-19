package com.muyuan.member.domain.query;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.member.domain.entity.UserEntity;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.repo.UserRepo;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserQuery {

    private UserRepo userRepo;

    /**
     * 通过UserNO 获取用户信息
     * @param user
     * @return
     */
    public Optional<User> get(User user) {
        Assert.isTrue(user != null,"sys user query  is null");

        SqlBuilder sqlBuilder = new SqlBuilder(User.class);
        if (ObjectUtils.isNotEmpty(user.getId())) {
            sqlBuilder.eq("id", user.getId());
        }
        if (ObjectUtils.isNotEmpty(user.getUsername())) {
            sqlBuilder.eq("username", user.getUsername());
        }
        sqlBuilder.eq("status",0);

        user = userRepo.selectOne(sqlBuilder.build());
        if (null == user) {
            return Optional.empty();
        }
        return Optional.of(user);
    }


}
