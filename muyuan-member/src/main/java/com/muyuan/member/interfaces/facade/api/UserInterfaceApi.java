package com.muyuan.member.interfaces.facade.api;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.member.api.UserInterface;
import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.application.query.RoleQuery;
import com.muyuan.member.application.query.UserQuery;
import com.muyuan.member.interfaces.assembler.UserInfoAssembler;
import com.muyuan.member.interfaces.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@Service(group = ServiceTypeConst.MEMBER_SERVICE,version = "1.0")
public class UserInterfaceApi implements UserInterface {

    @Autowired
    UserQuery userQuery;

    @Autowired
    RoleQuery roleQuery;


    @Override
    public Result<UserDTO> getUserByUsername(String username) {
        final Optional<User> userInfo = userQuery.getUserByUsername(username);
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        User user = userInfo.get();
        Long id = user.getId();
        List<Role> roles = getUserRoles(id);

        List<String> roleNames = roles.stream().map(item -> SecurityConst.AUTHORITY_PREFIX+item.getName()).collect(Collectors.toList());

        UserDTO userDTO = UserInfoAssembler.buildUserDTO(userInfo.get());
        userDTO.setRoles(roleNames);
        return ResultUtil.success(userDTO);
    }

    private List<Role> getUserRoles(Long id) {
        return  roleQuery.getRoleByUserId(id);
    }
}
