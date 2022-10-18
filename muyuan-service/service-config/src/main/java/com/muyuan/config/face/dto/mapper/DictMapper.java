package com.muyuan.config.face.dto.mapper;

import com.muyuan.config.api.dto.*;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.DictQueryCommend;
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
public interface DictMapper {

    DictQueryCommend toCommend(DictQueryRequest request);

    DictTypeQueryCommand toCommend(DictTypeQueryRequest request);

    DictTypeCommand toCommend(DictTypeRequest request);

    DictDataDTO to(DictData dictData);

    List<DictDataDTO> to(List<DictData> dictData);

    DictTypeDTO to(DictType dictType);

    List<DictTypeDTO> toTypeDTO(List<DictType> dictTypes);

}
