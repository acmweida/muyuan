package com.muyuan.user.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.user.domain.model.entity.Dept;
import com.muyuan.user.domain.repo.DeptRepo;
import com.muyuan.user.domain.service.DeptService;
import com.muyuan.user.face.dto.DeptCommand;
import com.muyuan.user.face.dto.DeptQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName MenuDomainServiceImpl
 * Description 权限
 * @Author 2456910384
 * @Date 2022/10/12 14:55
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class DeptServiceImpl implements DeptService {

    private DeptRepo deptRepo;

    @Override
    public List<Dept> list(DeptQueryCommand command) {
        List<Dept> list = deptRepo.list(command);
        return list;
    }

    @Override
    public String checkUnique(Dept.Identify identify) {
        Long id = null == identify.getId() ? 0 : identify.getId();
        Dept menu = deptRepo.selectDept(identify);
        if (null != menu && !id.equals(menu.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addDept(DeptCommand command,Dept parentDept) {
        Dept dept = new Dept();

        dept.setName(command.getName());
        dept.setParentId(command.getParentId());
        dept.setOrderNum(command.getOrderNum());
        dept.setDelFlag("0");
        dept.setEmail(command.getEmail());
        dept.setPhone(command.getPhone());
        dept.setCreateTime(DateTime.now().toDate());
        dept.setCreateBy(command.getCreateBy());

        deptRepo.addDept(dept);

        if (ObjectUtils.isNotEmpty(parentDept)) {
            dept.setParent(parentDept);
            dept.rebuildAncestors();

            deptRepo.updateDept(dept);
        }

        return true;
    }

    @Override
    public Optional<Dept> getDept(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return deptRepo.selectDept(id_);
                });
    }

}
