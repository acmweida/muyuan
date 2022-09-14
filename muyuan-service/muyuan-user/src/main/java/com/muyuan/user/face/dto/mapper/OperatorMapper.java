package com.muyuan.user.face.dto.mapper;

import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;
import com.muyuan.user.domain.model.entity.user.Operator;
import com.muyuan.user.face.dto.OperatorQueryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @ClassName OperatorMapper
 * Description DTO 转换
 * @Author 2456910384
 * @Date 2022/9/14 9:09
 * @Version 1.0
 */
@Mapper
public interface OperatorMapper {

    OperatorQueryCommand toCommand(OperatorQueryRequest request);

    @Mappings({
            @Mapping(target = "id",source = "id.value"),
            @Mapping(target = "username",source = "username.value")
    })
    OperatorDTO toDto(Operator operator);
}
