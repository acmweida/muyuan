package com.muyuan.manager.system.dto.converter;

import com.muyuan.manager.system.dto.OperatorParams;
import com.muyuan.manager.system.dto.PermissionParams;
import com.muyuan.manager.system.dto.vo.PermissionVO;
import com.muyuan.user.api.dto.OperatorRequest;
import com.muyuan.user.api.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @ClassName MenuConverter
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 17:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface OperatorConverter {

    OperatorRequest to(OperatorParams params);

}
