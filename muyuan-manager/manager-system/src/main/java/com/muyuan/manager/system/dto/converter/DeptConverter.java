package com.muyuan.manager.system.dto.converter;

import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.config.api.dto.DictTypeDTO;
import com.muyuan.manager.system.dto.DeptParams;
import com.muyuan.manager.system.dto.vo.DictDataVO;
import com.muyuan.manager.system.dto.vo.DictTypeVO;
import com.muyuan.user.api.dto.DeptRequest;
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
public interface DeptConverter {

    DeptRequest toRequest(DeptParams params);
}
