package com.muyuan.member.api;

import com.muyuan.common.constant.ServiceTypeConst;
import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.interfaces.facade.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = ServiceTypeConst.MEMBER_SERVICE,path = "/user",fallback = UserInterface.UserFallbackFactory.class)
public interface UserInterface {

    @RequestMapping(value = "/getUserByAccount",method = RequestMethod.POST)
    Result<UserDTO> getUserByUsername(@RequestParam("account") String username);

    /**
     * 熔断工厂
     *
     * @author siguiyang
     */
    @Component
    class UserFallbackFactory implements FallbackFactory<UserInterface> {

        @Override
        public UserInterface create(Throwable cause) {
            return new UserInterface() {
                @Override
                public Result getUserByUsername(String username) {
                    return ResultUtil.renderError();
                }
            };
        }
    }
}
