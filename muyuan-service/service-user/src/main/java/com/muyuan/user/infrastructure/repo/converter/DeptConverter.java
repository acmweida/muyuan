package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.user.domain.model.entity.Dept;
import com.muyuan.user.infrastructure.repo.dataobject.DeptDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @ClassName OperatorConverter
 * Description DO转换
 * @Author 2456910384
 * @Date 2022/9/14 10:38
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    Dept to(DeptDO deptDO);

    List<Dept> to(List<DeptDO> menuDOS);

    DeptDO to(Dept menuDO);

}
