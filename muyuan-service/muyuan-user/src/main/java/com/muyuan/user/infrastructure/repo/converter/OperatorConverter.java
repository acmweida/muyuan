package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.user.domain.model.entity.user.Operator;
import com.muyuan.user.infrastructure.repo.dataobject.OperatorDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @ClassName OperatorConverter
 * Description DO转换
 * @Author 2456910384
 * @Date 2022/9/14 10:38
 * @Version 1.0
 */
@Mapper
public interface OperatorConverter {

    @Mappings({
            @Mapping(source = "id",target ="id.value"),
            @Mapping(source = "username",target ="username.value")
    })
    Operator to(OperatorDO operatorDO);

}
