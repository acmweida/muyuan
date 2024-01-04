package com.muyuan.system.dto.converter;

import com.muyuan.system.dto.DeptParams;
import com.muyuan.user.api.dto.DeptRequest;
import org.mapstruct.Mapper;

/**
 * @ClassName MenuConverter
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 17:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptRequest toRequest(DeptParams params);
}
