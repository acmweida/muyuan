package com.muyuan.auth.dto.converter;

import com.muyuan.auth.dto.User;
import com.muyuan.user.api.dto.OperatorDTO;
import org.mapstruct.Mapper;

/**
 * @ClassName UserConverter 接口
 * Description UserConverter
 * @Author 2456910384
 * @Date 2022/10/11 10:43
 * @Version 1.0
 */
@Mapper
public interface UserConverter {

    User to(OperatorDTO operatorDTO);
}
