package com.muyuan.system.domain.service;

import com.muyuan.system.domain.model.SysDept;
import com.muyuan.system.interfaces.dto.SysDeptDTO;

import java.util.List;

/**
 * @ClassName SysDeptDomainService
 * Description SysDept domain sercice
 * @Author 2456910384
 * @Date 2022/5/13 11:07
 * @Version 1.0
 */
public interface SysDeptDomainService {

    /**
     * 列表查询
     * @param sysDeptDTO
     * @return
     */
    List<SysDept> list(SysDeptDTO sysDeptDTO);

    String checkUnique(SysDept sysDeptDTO);

    void add(SysDeptDTO sysDeptDTO);
}
