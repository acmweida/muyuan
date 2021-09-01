package com.muyuan.member.service.impl;

import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import com.muyuan.member.model.User;
import com.muyuan.member.repo.dao.UserMapper;
import com.muyuan.member.service.UserService;
import com.muyuan.member.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<UserVO> getUserInfo(String userNo) {
        final User user = userMapper.selectFirst(new SqlBuilder(User.class)
                .eq("userNo", userNo)
                .build());
        if (null == user) {
            return Optional.empty();
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return Optional.of(userVO);
    }
}
