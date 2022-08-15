package com.muyuan.store.system.interfaces.assembler;

import com.muyuan.store.system.domains.model.User;
import com.muyuan.store.system.domains.vo.UserVO;
import com.muyuan.member.interfaces.to.UserTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

public class UserInfoAssembler {

    public static UserTO buildUserTO(User user) {
        UserTO userTO = new UserTO();
        BeanUtils.copyProperties(user, userTO);
        return userTO;
    }

    public static UserVO buildUserVO(User user, List<String> roleNames, Set<String> perms) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);

        userVO.setRoles(roleNames);
        userVO.setPermissions(perms);
        return userVO;
    }
}
