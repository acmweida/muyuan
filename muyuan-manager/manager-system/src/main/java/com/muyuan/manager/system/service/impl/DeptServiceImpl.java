package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.manager.system.dto.DeptQueryParams;
import com.muyuan.manager.system.service.DeptService;
import com.muyuan.user.api.DeptInterface;
import com.muyuan.user.api.dto.DeptDTO;
import com.muyuan.user.api.dto.DeptQueryRequest;
import com.muyuan.user.api.dto.DeptRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysDeptDomainServiceImpl
 * Description SysDeptDomainServiceImpl
 * @Author 2456910384
 * @Date 2022/5/13 11:08
 * @Version 1.0
 */
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private DeptInterface deptInterface;


    @Override
    public List<DeptDTO> list(DeptQueryParams params) {
        Result<List<DeptDTO>> result = deptInterface.list(DeptQueryRequest.builder()
                .name(params.getName())
                .status(params.getStatus())
                .build());

        return ResultUtil.getOr(result, ArrayList::new);
    }

    @Override
    public Result add(DeptRequest request) {
        return deptInterface.addDept(request);
    }
}
