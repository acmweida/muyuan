package com.muyuan.system.dto.converter;

import com.muyuan.system.dto.OperLogParams;
import com.muyuan.system.dto.vo.OperLogVO;
import com.muyuan.user.api.dto.OperLogDTO;
import com.muyuan.user.api.dto.OperLogRequest;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName OperLogConverter
 * Description
 * @Author  ${author}
 * @Date 2022-12-15T15:27:12.638+08:00
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface OperLogConverter {


    List<OperLogVO> toVO(List<OperLogDTO> operLogVOS);

    OperLogRequest to(OperLogParams params);

    OperLogVO toVO(OperLogDTO operLogDTO);

}
