package com.muyuan.system.domain.service.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.domain.model.SysDept;
import com.muyuan.system.domain.repo.SysDeptRepo;
import com.muyuan.system.domain.service.SysDeptDomainService;
import com.muyuan.system.interfaces.dto.SysDeptDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysDeptDomainServiceImpl
 * Description SysDeptDomainServiceImpl
 * @Author 2456910384
 * @Date 2022/5/13 11:08
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class SysDeptDomainServiceImpl implements SysDeptDomainService {

    private SysDeptRepo sysDeptRepo;


    @Override
    public List<SysDept> list(SysDeptDTO sysDeptDTO) {
        SqlBuilder sqlBuilder = new SqlBuilder(SysDept.class)
                .eq("name", sysDeptDTO.getName())
                .eq("status", sysDeptDTO.getStatus())
                .orderByAsc("orderNum");
        List<SysDept> list = sysDeptRepo.select(sqlBuilder.build());
        return list;
    }
}
