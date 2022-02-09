package com.muyuan.common.core.api;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.TokenStatus;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = ServiceTypeConst.COMMON_SERVICE,path = "/token",fallback = TokenInterface.TokenFallbackFactory.class)
public interface TokenInterface {

    @GetMapping("/verify")
    Result<TokenStatus> verify(@RequestParam("token") String token);

    /**
     * 熔断工厂
     */
    class TokenFallbackFactory implements FallbackFactory<TokenInterface>  {

        @Override
        public TokenInterface create(Throwable cause) {
            return new TokenInterface() {
                @Override
                public Result<TokenStatus> verify(String token) {
                    return ResultUtil.render();
                }
            };
        }
    }
}
