package com.muyuan.user.api;

import com.muyuan.common.bean.Result;
import com.muyuan.user.api.dto.DeptDTO;
import com.muyuan.user.api.dto.DeptQueryRequest;
import com.muyuan.user.api.dto.DeptRequest;

import java.util.List;

/**
 * @ClassName DeptInterface
 * Description 部门接口
 * @Author 2456910384
 * @Date 2022/10/13 11:06
 * @Version 1.0
 */
public interface DeptInterface {

    /**
     * 菜单列表查询
     * @param request
     * @return
     */
    Result<List<DeptDTO>> list(DeptQueryRequest request);

    /**
     * 添加部门
     * @param request
     * @return
     */
    Result addDept(DeptRequest request);

}
