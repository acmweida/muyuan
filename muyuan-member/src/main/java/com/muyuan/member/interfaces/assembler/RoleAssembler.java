package com.muyuan.member.interfaces.assembler;

import com.muyuan.member.application.vo.RoleVO;
import com.muyuan.member.domain.model.Role;
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
