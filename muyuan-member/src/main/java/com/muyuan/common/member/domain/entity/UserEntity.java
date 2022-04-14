package com.muyuan.common.member.domain.entity;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.member.domain.model.User;
import lombok.Data;

import java.util.Random;

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

}
