package com.muyuan.config.face.dto.transfer;

import com.muyuan.config.api.dto.*;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.DictDataCommand;
import com.muyuan.config.face.dto.DictQueryCommand;
import com.muyuan.config.face.dto.DictTypeCommand;
import com.muyuan.config.face.dto.DictTypeQueryCommand;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName DictMapper
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 10:46
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DictTransfer {

    DictQueryCommand toCommand(DictQueryRequest request);

    DictTypeQueryCommand toCommand(DictTypeQueryRequest request);

    DictDataCommand toCommand(DictDataRequest request);

    DictTypeCommand toCommand(DictTypeRequest request);

    DictDataDTO to(DictData dictData);

    List<DictDataDTO> to(List<DictData> dictData);

    DictTypeDTO to(DictType dictType);

    List<DictTypeDTO> toTypeDTO(List<DictType> dictTypes);

}
