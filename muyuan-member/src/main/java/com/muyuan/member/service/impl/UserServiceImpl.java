package com.muyuan.member.service.impl;

import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import com.muyuan.common.util.EncryptUtil;
import com.muyuan.common.util.IdUtil;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.model.User;
import com.muyuan.member.repo.dao.UserMapper;
import com.muyuan.member.service.UserService;
import com.muyuan.member.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public int accountRegister(RegisterDTO registerInfo) {

        User account = userMapper.selectFirst(new SqlBuilder(User.class).select("id")
                .eq("account", registerInfo.getAccount())
                .build());
        if (null != account) {
            return 1;
        }

        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        User user = new User();
        BeanUtils.copyProperties(registerInfo,user);
        user.setUserNo(IdUtil.createUserNo());
        user.setUsername(IdUtil.createUserName());
        user.setPassword(EncryptUtil.SHA1(registerInfo.getPassword() + salt, encryptKey));;
        user.setSalt(salt);
        user.setEncryptKey(encryptKey);

        userMapper.insert(user);

        return 0;
    }
}
