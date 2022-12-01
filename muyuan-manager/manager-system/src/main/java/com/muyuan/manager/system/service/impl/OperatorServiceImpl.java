package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.system.dto.OperatorQueryParams;
import com.muyuan.manager.system.model.SysUser;
import com.muyuan.manager.system.repo.SysUserRepo;
import com.muyuan.manager.system.service.OperatorService;
import com.muyuan.user.api.OperatorInterface;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;
import com.muyuan.user.api.dto.OperatorRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class OperatorServiceImpl implements OperatorService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private OperatorInterface operatorInterface;

    @Autowired
    private SysUserRepo sysUserRepo;

    @Override
    public  Page<OperatorDTO> list(OperatorQueryParams params) {

        OperatorQueryRequest request = OperatorQueryRequest.builder()
                .status(params.getStatus())
                .username(params.getUsername())
                .phone(params.getPhone())
                .platformType(PlatformType.trance(params.getPlatformType()))
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<OperatorDTO>> res = operatorInterface.list(request);

        return res.getData();
    }

    @Override
    public Page<SysUser> selectAllocatedList(OperatorQueryParams operatorQueryParams) {
        Page page = Page.builder()
                .pageNum(operatorQueryParams.getPageNum())
                .pageSize(operatorQueryParams.getPageSize()).build();

        List<SysUser> sysUsers = sysUserRepo.selectAllocatedList(new SqlBuilder()
                .eq("username", operatorQueryParams.getUsername())
                .eq("phone", operatorQueryParams.getPhone())
                .page(page)
                .build());

        page.setRows(sysUsers);

        return page;
    }

    @Override
    public Page<SysUser> selectUnallocatedList(OperatorQueryParams operatorQueryParams) {
        Page page = Page.builder()
                .pageNum(operatorQueryParams.getPageNum())
                .pageSize(operatorQueryParams.getPageSize()).build();

        List<SysUser> sysUsers = sysUserRepo.selectUnallocatedList(new SqlBuilder()
                .eq("username", operatorQueryParams.getUsername())
                .eq("phone", operatorQueryParams.getPhone())
                .page(page)
                .build());

        page.setRows(sysUsers);

        return page;
    }

    @Override
    public Optional<OperatorDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<OperatorDTO> permissioHander = operatorInterface.get(id_);
                    return ResultUtil.getOr(permissioHander, null);
                });
    }

    @Override
    public Result add(OperatorRequest request) {
        return operatorInterface.add(request);
    }

}
