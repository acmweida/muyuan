package com.muyuan.user.api;

import com.muyuan.common.core.result.Result;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;

import java.util.List;
import java.util.Set;

/**
 * @ClassName OperatorInterface 接口
 * Description 运营人员用户信息接口
 * @Author 2456910384
 * @Date 2022/9/13 16:20
 * @Version 1.0
 */
public interface OperatorInterface {

    Result<OperatorDTO> getOperatorByUsername(OperatorQueryRequest request);

    Set<String> getMenuPermissionByRoleCodes(List<String> roleIds);

    void linkShop(Long shopId);
}
