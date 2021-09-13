package com.muyuan.member.api;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.interfaces.facade.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "member",path = "/user",fallback = UserInterFace.UserFallbackFactory.class)
public interface UserInterFace {

    @RequestMapping(value = "/getUserByUsername",method = RequestMethod.POST)
    Result<UserDTO> getUserByUsername(@RequestParam("username") String username);

    /**
     * 熔断工厂
     *
     * @author siguiyang
     */
    @Component
    class UserFallbackFactory implements FallbackFactory<UserInterFace> {

        @Override
        public UserInterFace create(Throwable cause) {
            return new UserInterFace() {
                @Override
                public Result getUserByUsername(String username) {
                    return ResultUtil.renderError();
                }
            };
        }
    }
}
