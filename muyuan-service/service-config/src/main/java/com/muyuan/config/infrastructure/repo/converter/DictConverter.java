package com.muyuan.config.infrastructure.repo.converter;

import com.muyuan.config.domains.model.entity.DictData;
import com.muyuan.config.infrastructure.repo.dataobject.DictDataDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName DictConverter 接口
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 11:16
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    DictData to(DictDataDO dictDataDTO);

    List<DictData> to(List<DictDataDO> dictDataDTO);

}
