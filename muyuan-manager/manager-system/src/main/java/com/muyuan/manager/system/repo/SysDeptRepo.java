package com.muyuan.manager.system.repo;

import com.muyuan.manager.system.model.SysDept;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysDeptRepo
 * Description SysDeptRepo
 * @Author 2456910384
 * @Date 2022/5/13 11:13
 * @Version 1.0
 */
public interface SysDeptRepo {

    List<SysDept> select(Map params);

    SysDept selectOne(Map params);

    void insert(SysDept sysMenu);

    void updateById(SysDept sysDept);
}
