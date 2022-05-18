package com.muyuan.system.domain.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.entity.SysDeptEntity;
import com.muyuan.system.domain.model.SysDept;
import com.muyuan.system.interfaces.dto.SysDeptDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @ClassName SysDeptFactory
 * Description SysDept 工厂类
 * @Author 2456910384
 * @Date 2022/5/18 14:11
 * @Version 1.0
 */
public class SysDeptFactory {

    public static SysDept newInstance(SysDeptDTO sysDeptDTO) {
        SysDept sysDept = buildInstance(sysDeptDTO);
        sysDept.setCreateTime(new Date());
        sysDept.setCreateBy(SecurityUtils.getUsername());
        sysDept.setCreateById(SecurityUtils.getUserId());
        return sysDept;
    }

    public static SysDept buildInstance(SysDeptDTO sysDeptDTO) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(sysDeptDTO,sysDept);
        if (ObjectUtils.isEmpty(sysDeptDTO.getStatus())) {
            sysDept.setStatus("0");
        }
        return sysDept;
    }

    public static SysDeptEntity buildEntity(SysDept sysDept) {
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        BeanUtils.copyProperties(sysDept,sysDeptEntity);
        return sysDeptEntity;
    }


}
