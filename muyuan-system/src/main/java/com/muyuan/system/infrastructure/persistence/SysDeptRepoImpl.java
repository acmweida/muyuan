package com.muyuan.system.infrastructure.persistence;

import com.muyuan.system.domain.model.SysDept;
import com.muyuan.system.domain.model.SysMenu;
import com.muyuan.system.domain.repo.SysDeptRepo;
import com.muyuan.system.infrastructure.persistence.dao.SysDeptMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysDeptRepoImpl
 * Description SysDeptRepoImpl
 * @Author 2456910384
 * @Date 2022/5/13 11:17
 * @Version 1.0
 */
@AllArgsConstructor
@Slf4j
@Component
public class SysDeptRepoImpl implements SysDeptRepo {

    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> select(Map params) {
        return sysDeptMapper.selectList(params);
    }
}
