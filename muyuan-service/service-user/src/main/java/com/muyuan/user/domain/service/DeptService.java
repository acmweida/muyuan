package com.muyuan.user.domain.service;

import com.muyuan.user.domain.model.entity.Dept;
import com.muyuan.user.face.dto.DeptCommand;
import com.muyuan.user.face.dto.DeptQueryCommand;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DeptService 接口
 * Description DeptService
 * @Author 2456910384
 * @Date 2022/11/28 16:10
 * @Version 1.0
 */
public interface DeptService {

    /**
     * 列表查询
     * @param command
     * @return
     */
    List<Dept> list(DeptQueryCommand command);

    /**
     * 检查唯一性
     *
     * @param identify
     * @return
     */
    String checkUnique(Dept.Identify identify);

    boolean addDept(DeptCommand command,Dept parentDept);

    Optional<Dept> getDept(Long id);
}
