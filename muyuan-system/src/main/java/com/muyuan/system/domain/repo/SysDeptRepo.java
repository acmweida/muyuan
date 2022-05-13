package com.muyuan.system.domain.repo;

import com.muyuan.system.domain.model.SysDept;

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
}
