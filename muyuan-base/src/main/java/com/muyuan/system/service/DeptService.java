package com.muyuan.system.service;

import com.muyuan.common.bean.Result;
import com.muyuan.system.dto.DeptQueryParams;
import com.muyuan.user.api.dto.DeptDTO;
import com.muyuan.user.api.dto.DeptRequest;

import java.util.List;

/**
 * @ClassName SysDeptDomainService
 * Description SysDept domain sercice
 * @Author 2456910384
 * @Date 2022/5/13 11:07
 * @Version 1.0
 */
public interface DeptService {

    /**
     * 列表查询
     * @param params
     * @return
     */
    List<DeptDTO> list(DeptQueryParams params);

    Result add(DeptRequest request);
}
