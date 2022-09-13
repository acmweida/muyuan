package com.muyuan.user.api;

import com.muyuan.common.core.result.Result;
import com.muyuan.user.api.dto.MerchantDTO;
import com.muyuan.user.api.dto.OperatorDTO;

import java.util.List;
import java.util.Set;

/**
 * @ClassName UserInterface 接口
 * Description TODO
 * @Author 2456910384
 * @Date 2022/9/13 16:20
 * @Version 1.0
 */
public interface OperatorInterface {

    Result<OperatorDTO> getUserByUsername(String username);

    Set<String> getMenuPermissionByRoleCodes(List<String> roleIds);

    void linkShop(Long shopId);
}
