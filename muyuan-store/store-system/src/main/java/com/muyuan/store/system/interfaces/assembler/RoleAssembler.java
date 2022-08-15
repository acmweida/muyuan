package com.muyuan.store.system.interfaces.assembler;

import com.muyuan.store.system.domains.model.Role;
import com.muyuan.store.system.domains.vo.RoleVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleAssembler {

    public static List<RoleVO> buildRoleVO(List<Role> sysRoles) {
        List<RoleVO> list = new ArrayList<>();
        for (Role dictData: sysRoles) {
            if (null != dictData) {
                RoleVO temp = new RoleVO();
                BeanUtils.copyProperties(dictData,temp);
                switch (temp.getStatus()) {
                    case "1":
                        temp.setStatus("禁用");
                    case "0":
                        temp.setStatus("正常");
                }
                list.add(temp);
            }
        }

        return list;
    }
}
