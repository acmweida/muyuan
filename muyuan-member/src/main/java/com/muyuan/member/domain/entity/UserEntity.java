package com.muyuan.member.domain.entity;

import com.muyuan.common.core.util.EncryptUtil;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.member.domain.model.User;
import lombok.Data;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Data
public class UserEntity extends User {

    private static final String NAME_PREFIX = "my_";

    private static Random random = new Random();

    public static String createUserName() {
        StringBuffer name = new StringBuffer(NAME_PREFIX);
        name.append(StrUtil.randomString(7));
        name.append((random.nextInt(900)+100));
        return name.toString();
    }

    public UserEntity(String username,String password) {
        super(username,password);
    }

    /**
     * 初始化用户信息
     */
    public void initInstance() {
        Assert.isTrue(!ObjectUtils.isEmpty(getPassword()),"SysUserEntity init fail, password is null");
        String salt = UUID.randomUUID().toString();
        String encryptKey = UUID.randomUUID().toString();

        setNickName(createUserName());
        setPassword(EncryptUtil.SHA1(getPassword() + salt, encryptKey));;
        setSalt(salt);
        setEncryptKey(encryptKey);

        setCreateTime(new Date());
        setCreateBy(SecurityUtils.getUserId());
    }

}
