package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.system.dto.OperatorParams;
import com.muyuan.manager.system.dto.OperatorQueryParams;
import com.muyuan.manager.system.model.SysUser;
import com.muyuan.manager.system.model.SysUserRole;
import com.muyuan.manager.system.repo.SysUserRepo;
import com.muyuan.manager.system.service.OperatorService;
import com.muyuan.user.api.OperatorInterface;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
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
    public Optional<SysUser> getByyUsername(String username) {
        final Optional<SysUser> userInfo = get(new SysUser(username));
        if (!userInfo.isPresent()) {
            return Optional.empty();
        }
        return userInfo;
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
    @Transactional
    public void add(OperatorParams userQueryParams) {
        SysUser sysUser = new SysUser();
        sysUserRepo.insert(sysUser);

        FunctionUtil.getIfNotNullThen(
                () -> userQueryParams.getRoleIds(),
                (v) -> {
                    Long[] roles = (Long[]) v;
                    List<SysUserRole> list = new ArrayList<>(roles.length);
                    for (Long role : roles) {
                        list.add(SysUserRole.builder().userId(sysUser.getId()).roleId(role).build());
                    }
                    sysUserRepo.batchInsert(list);
                }
        );

    }

    @Override
    public String checkAccountNameUnique(SysUser sysUser) {
        Long id = null == sysUser.getId() ? 0 : sysUser.getId();
        SysUser account = sysUserRepo.selectOne(new SqlBuilder(SysUser.class).select("id")
                .eq("username", sysUser.getUsername())
                .build());
        if (null != account && !id.equals(account.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    /**
     * 通过UserNO 获取用户信息
     *
     * @param sysUser
     * @return
     */
    public Optional<SysUser> get(SysUser sysUser) {
        Assert.isTrue(sysUser != null, "sys user query  is null");

        SqlBuilder sqlBuilder = new SqlBuilder(SysUser.class)
                .eq("id", sysUser.getId())
                .eq("username", sysUser.getUsername())
                .eq("status", 0);

        final SysUser user = sysUserRepo.selectOne(sqlBuilder.build());
        if (null == user) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

}
