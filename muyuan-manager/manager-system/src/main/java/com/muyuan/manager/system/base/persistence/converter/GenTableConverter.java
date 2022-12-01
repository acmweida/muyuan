package com.muyuan.manager.system.base.persistence.converter;

import com.muyuan.manager.system.dto.GenTableDTO;
import com.muyuan.manager.system.model.GenTable;
import org.mapstruct.Mapper;

/**
 * @ClassName GenTableConverter
 * Description
 * @Author 2456910384
 * @Date 2022/11/30 9:44
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface GenTableConverter {

    GenTable to(GenTableDTO genTableDTO);
}
