package com.muyuan.user.face.dto.mapper;

import com.muyuan.user.api.dto.OperLogDTO;
import com.muyuan.user.api.dto.OperLogQueryRequest;
import com.muyuan.user.api.dto.OperLogRequest;
import com.muyuan.user.domain.model.entity.OperLog;
import com.muyuan.user.face.dto.OperLogCommand;
import com.muyuan.user.face.dto.OperLogQueryCommand;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName OperatorMapper
 * Description DTO 转换
 * @Author 2456910384
 * @Date 2022/9/14 9:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface OperLogMapper {

    OperLogQueryCommand toCommand(OperLogQueryRequest request);

    OperLogCommand toCommand(OperLogRequest request);

    List<OperLogDTO> toDTO(List<OperLog> operLog);

    OperLogDTO toDTO(OperLog operLog);

}
