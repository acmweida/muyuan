package com.muyuan.common.web.common;

import com.muyuan.common.core.common.OptProvider;
import com.muyuan.common.valueobject.Opt;
import com.muyuan.common.web.util.SecurityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class WebOptProvider implements OptProvider {
    @Override
    public Opt currentOpt() {
        return new Opt(SecurityUtils.getUserId(),SecurityUtils.getUsername());
    }
}
