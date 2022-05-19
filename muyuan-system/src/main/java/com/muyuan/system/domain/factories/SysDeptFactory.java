package com.muyuan.system.domain.factories;

import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.system.domain.entity.SysDeptEntity;
import com.muyuan.system.interfaces.dto.SysDeptDTO;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName SysDeptFactory
 * Description SysDept 工厂类
 * @Author 2456910384
 * @Date 2022/5/18 14:11
 * @Version 1.0
 */
public class SysDeptFactory {

    public static SysDeptEntity newInstance(SysDeptDTO sysDeptDTO) {
        SysDeptEntity sysDept = sysDeptDTO.convert();
        sysDept.setCreateTime(new Date());
        sysDept.setCreateBy(SecurityUtils.getUsername());
        sysDept.setCreateById(SecurityUtils.getUserId());
        if (Objects.isNull(sysDept.getStatus())) {
            sysDept.setStatus("0");
        }
        return sysDept;
    }

}
