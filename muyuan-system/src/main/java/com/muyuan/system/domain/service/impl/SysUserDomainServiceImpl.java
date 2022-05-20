package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.entity.SysUserEntity;
import com.muyuan.system.domain.factories.SysUserFactory;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.model.SysUserRole;
import com.muyuan.system.domain.repo.SysUserRepo;
import com.muyuan.system.domain.service.SysUserDomainService;
import com.muyuan.system.interfaces.dto.SysUserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class SysUserDomainServiceImpl implements SysUserDomainService {

    private SysUserRepo sysUserRepo;

    @Override
    public  Page<SysUser> list(SysUserDTO sysUserTO) {

        Page page = Page.builder().pageNum(sysUserTO.getPageNum())
                .pageSize(sysUserTO.getPageSize()).build();

        SqlBuilder sqlBuilder = new SqlBuilder(SysUser.class)
                .eq("username", sysUserTO.getUsername())
                .eq("status", sysUserTO.getStatus())
                .eq("phone", sysUserTO.getStatus())
                .eq("deptId", sysUserTO.getDeptId())
                .gte("createTime", sysUserTO.getBeginCreateTime())
                .lte("createTime", sysUserTO.getEndCreateTime())
                .page(page);

        List<SysUser> list = sysUserRepo.select(sqlBuilder.build());

        page.setRows(list);
        return page;
    }

    @Override
    public Page<SysUser> selectAllocatedList(SysUserDTO sysUserDTO) {
        Page page = Page.builder()
                .pageNum(sysUserDTO.getPageNum())
                .pageSize(sysUserDTO.getPageSize()).build();

        List<SysUser> sysUsers = sysUserRepo.selectAllocatedList(new SqlBuilder()
                .eq("roleId", sysUserDTO.getRoleId())
                .eq("username", sysUserDTO.getUsername())
                .eq("phone", sysUserDTO.getPhone())
                .page(page)
                .build());

        page.setRows(sysUsers);

        return page;
    }

    @Override
    public Page<SysUser> selectUnallocatedList(SysUserDTO sysUserDTO) {
        Page page = Page.builder()
                .pageNum(sysUserDTO.getPageNum())
                .pageSize(sysUserDTO.getPageSize()).build();

        List<SysUser> sysUsers = sysUserRepo.selectUnallocatedList(new SqlBuilder()
                .eq("roleId", sysUserDTO.getRoleId())
                .eq("username", sysUserDTO.getUsername())
                .eq("phone", sysUserDTO.getPhone())
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
    public Optional<SysUser> getByyId(Long userId) {
        final Optional<SysUser> userInfo = get(new SysUser(userId));
        if (!userInfo.isPresent()) {
            return Optional.empty();
        }
        return userInfo;
    }

    @Override
    @Transactional
    public void add(SysUserDTO sysUserDTO) {
        SysUserEntity sysUser = SysUserFactory.newInstance(sysUserDTO);
        sysUserRepo.insert(sysUser);

        FunctionUtil.getIfNotNullThen(
                () -> sysUserDTO.getRoleIds(),
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
