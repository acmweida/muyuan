package com.muyuan.member.infrastructure.persistence;

import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.repo.UserRepo;
import com.muyuan.member.infrastructure.persistence.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRepoImpl implements UserRepo {

    @Autowired
    UserMapper userMapper;

    @Override
    public User find(int userNo) {
        return userMapper.find(userNo);
    }

    @Override
    public User selectOne(Map params) {
        return userMapper.selectOne(params);
    }

    @Override
    public boolean insert(User dataObject) {
       return userMapper.insert(dataObject)  > 0;
    }
}
