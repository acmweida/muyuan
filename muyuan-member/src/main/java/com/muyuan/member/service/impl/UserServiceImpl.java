package com.muyuan.member.service.impl;

import com.muyuan.common.repo.jdbc.crud.SqlBuilder;
import com.muyuan.common.util.EncryptUtil;
import com.muyuan.common.util.JWTUtil;
import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.model.User;
import com.muyuan.member.repo.dao.UserMapper;
import com.muyuan.member.service.UserService;
import com.muyuan.member.vo.AccountLoginVo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<AccountLoginVo> login(AccountLoginDTO loginInfo) {
        AccountLoginVo loginVo = new AccountLoginVo();
        User account = userMapper.selectFirst(new SqlBuilder(User.class)
                .eq("account", loginInfo.getAccount())
                .build());

        if (account == null) {
            return Optional.empty();
        }

        final String password = EncryptUtil.SHA1(loginInfo.getAccount() + account.getSalt(), account.getEncryptKey());

        if (!account.getPassword().equals(password)) {
            return Optional.empty();
        }
        Map claims = new HashMap();
        claims.put("account",account.getAccount());
        DateTime time = DateTime.now();
        time.plus(30*60*60*1000);
        String jwt = JWTUtil.createJwt(time.toDate(), claims);
        loginVo.setToken(jwt);
        loginVo.setExpireTime(time.toDate());
        return Optional.of(loginVo);
    }

    @Override
    public int register(RegisterDTO registerInfo) {

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
        user.setPassword(EncryptUtil.SHA1(registerInfo.getPassword() + salt, encryptKey));;
        user.setSalt(salt);
        user.setEncryptKey(encryptKey);


        userMapper.insert(user);

        return 0;
    }
}
