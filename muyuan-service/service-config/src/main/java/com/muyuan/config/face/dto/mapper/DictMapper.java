package com.muyuan.config.face.dto.mapper;

import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.config.api.dto.DictQueryRequest;
import com.muyuan.config.domains.model.entity.DictData;
import com.muyuan.config.face.dto.DictQueryCommend;
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

    DictDataDTO to(DictData dictData);

    List<DictDataDTO> to(List<DictData> dictData);
}
