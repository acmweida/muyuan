package com.muyuan.user.domain.repo;

import com.muyuan.user.domain.model.entity.Dept;
import com.muyuan.user.face.dto.DeptQueryCommand;

import java.util.List;

public interface DeptRepo {

    List<Dept> list(DeptQueryCommand command);

    Dept selectDept(Dept.Identify identify);

    Dept selectDept(Long id);

    boolean addDept(Dept menu);

    /**
     *
     * @param menu
     * @return old value
     */
    Dept updateDept(Dept dept);

}
