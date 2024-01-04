package com.muyuan.system.dao.converter;

import com.muyuan.system.dto.GenTableDTO;
import com.muyuan.system.entity.GenTable;
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
