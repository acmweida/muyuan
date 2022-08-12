package com.muyuan.common.web.filter.dubbo;

import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.web.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @ClassName JwtProviderFilter
 * Description Dobbo RPC JWT 共享
 * @Author 2456910384
 * @Date 2022/7/25 11:43
 * @Version 1.0
 */
@Slf4j
@Activate(group = {CommonConstants.CONSUMER})
public class JwtConsumerFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (GenericService.class != invoker.getInterface()) {
            String jwtPayload = null;
            if (JwtUtils.hasJwtPayloadWeb()) {
                jwtPayload = JwtUtils.getJwtPayloadWeb();
            } else if (JwtUtils.hasJwtPayloadRpc()) {
                jwtPayload = JwtUtils.getJwtPayloadRpc();
            }
            if (ObjectUtils.isNotEmpty(jwtPayload)) {
                invocation.getAttachments().put(SecurityConst.JWT_PAYLOAD_KEY, jwtPayload);
            }
        }
        return invoker.invoke(invocation);
    }
}
