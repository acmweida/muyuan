package com.muyuan.store.system.interfaces.assembler;

import com.muyuan.store.system.domains.model.User;
import com.muyuan.store.system.domains.vo.UserVO;
import com.muyuan.user.api.dto.OperatorDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;

public class UserInfoAssembler {

    public static OperatorDTO buildUserTO(User user) {
        OperatorDTO userTO = new OperatorDTO();
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
