package com.muyuan.user.infrastructure.repo.converter;

import com.muyuan.user.domain.model.entity.OperLog;
import com.muyuan.user.infrastructure.repo.dataobject.OperLogDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName OperatorConverter
 * Description DO转换
 * @Author 2456910384
 * @Date 2022/9/14 10:38
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface OperLogConverter {

    OperLog to(OperLogDO operLogDO);

    List<OperLog> to(List<OperLogDO> operLogDOS);

    OperLogDO to(OperLog operLog);

}
