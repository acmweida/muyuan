package com.muyuan.manager.system.dto.assembler;

import com.muyuan.manager.system.dto.vo.SysRoleVO;
import com.muyuan.manager.system.domains.model.SysRole;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SysRoleAssembler {

    public static List<SysRoleVO> buildSysRoleVO(List<SysRole> sysRoles) {
        List<SysRoleVO> list = new ArrayList<>();
        for (SysRole dictData: sysRoles) {
            if (null != dictData) {
                SysRoleVO temp = new SysRoleVO();
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
