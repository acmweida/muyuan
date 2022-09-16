package com.muyuan.user.api.serialization;

import com.muyuan.common.core.enums.UserType;
import com.muyuan.user.api.dto.*;
import org.apache.dubbo.common.serialize.support.SerializationOptimizer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName SerializationOptimizerImpl
 * Description 序列化
 * @Author 2456910384
 * @Date 2022/9/14 15:40
 * @Version 1.0
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {

    @Override
    public Collection<Class<?>> getSerializableClasses() {
        List<Class<?>> classes = new LinkedList<>();
        classes.add(UserQueryRequest.class);
        classes.add(UserPwdRegisterRequest.class);
        classes.add(PermissionQueryRequest.class);
        classes.add(UserDTO.class);
        classes.add(UserType.class);
        return classes;
    }
}
