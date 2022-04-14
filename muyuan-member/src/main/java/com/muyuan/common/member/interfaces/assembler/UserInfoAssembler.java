package com.muyuan.common.member.interfaces.assembler;

import com.muyuan.common.member.domain.model.User;
import com.muyuan.common.member.domain.vo.UserVO;
import com.muyuan.common.member.interfaces.dto.UserDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

public class UserInfoAssembler {

    public static UserDTO buildUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public static UserVO buildUserVO(User user, List<String> roleNames, Set<String> perms) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);

        userVO.setRoles(roleNames);
        userVO.setPermissions(perms);
        return userVO;
    }
}
