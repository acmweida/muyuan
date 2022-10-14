package com.muyuan.manager.system.service;

import com.muyuan.manager.system.domains.model.SysDept;
import com.muyuan.manager.system.dto.SysDeptDTO;

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
