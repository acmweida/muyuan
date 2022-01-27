package com.muyuan.member.interfaces.assembler;

import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.vo.UserVO;
import com.muyuan.member.interfaces.dto.UserDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserInfoAssembler {

    public static UserDTO buildUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public static UserVO buildUserVO(User user, List<Role> roles) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);

        List<String> roleNames = roles.stream().map(item -> item.getName()).collect(Collectors.toList());

        userVO.setRoles(roleNames);
        return userVO;
    }
}
