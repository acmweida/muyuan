package com.muyuan.user.face.dto.mapper;

import com.muyuan.user.api.dto.DeptDTO;
import com.muyuan.user.api.dto.DeptQueryRequest;
import com.muyuan.user.api.dto.DeptRequest;
import com.muyuan.user.domain.model.entity.Dept;
import com.muyuan.user.face.dto.DeptCommand;
import com.muyuan.user.face.dto.DeptQueryCommand;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName DeptMapper 接口
 * Description DeptMapper
 * @Author 2456910384
 * @Date 2022/11/28 16:22
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DeptMapper {

    DeptQueryCommand toCommand(DeptQueryRequest request);

    DeptCommand toCommand(DeptRequest request);

    List<DeptDTO> toDTO(List<Dept> depts);
}
