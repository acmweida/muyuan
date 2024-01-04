package com.muyuan.system.dto.converter;

import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.config.api.dto.DictTypeDTO;
import com.muyuan.system.dto.vo.DictDataVO;
import com.muyuan.system.dto.vo.DictTypeVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName MenuConverter
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 17:09
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    DictTypeVO toVO(DictTypeDTO dictTypeDTO);

    DictDataVO toVO(DictDataDTO dictDataDTO);

    List<DictDataVO> toDictDataVO2(List<DictDataDTO> dictDatas);

}
