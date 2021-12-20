package com.muyuan.auth.service.impl;

import com.muyuan.auth.base.constant.LoginMessageConst;
import com.muyuan.common.result.Result;
import com.muyuan.member.api.UserInterface;
import com.muyuan.member.interfaces.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserInterface userInterFace;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserDTO> result  = userInterFace.getUserByUsername(username);

        if (result.getData() == null) {
            throw new UsernameNotFoundException(LoginMessageConst.USERNAME_PASSWORD_ERROR);
        }

        UserDTO userDTO = result.getData();
        List<GrantedAuthority>  authorities = new ArrayList<>();

        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ADMIN";
            }
        });
        return new com.muyuan.auth.dto.UserInfo(userDTO.getUsername(), userDTO.getPassword(),userDTO.getUsername(), authorities);
    }

}
