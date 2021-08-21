package com.muyuan.member.service.impl;

import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.model.User;
import com.muyuan.member.repo.dao.UserMapper;
import com.muyuan.member.service.UserService;
import com.muyuan.member.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<UserVO> login(AccountLoginDTO loginInfo) {
        User account = userMapper.selectFirst(new SqlBuilder(User.class)
                .eq("account", loginInfo.getAccount())
                .build());

        if (account == null) {
            return Optional.empty();
        }

        UserVO userVO = UserVO.builder().build();
        BeanUtils.copyProperties(account,userVO);

        return Optional.of(userVO);
    }
}
