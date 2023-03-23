package com.muyuan.common.core.common;

import com.muyuan.common.core.exception.UserNotFoundException;
import com.muyuan.common.valueobject.Opt;
import org.springframework.stereotype.Component;

@Component
public class DefaultOptProvider implements OptProvider {

    @Override
    public Opt currentOpt() {
        throw new UserNotFoundException();
    }
}
