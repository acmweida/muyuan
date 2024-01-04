package com.muyuan.system.dto.assembler;

import com.muyuan.system.dto.vo.RoleVO;
import com.muyuan.user.api.dto.RoleDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SysRoleAssembler {

    public static List<RoleVO> buildRoleVO(List<RoleDTO> roleDTOS) {
        List<RoleVO> list = new ArrayList<>();
        for (RoleDTO dictData: roleDTOS) {
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
