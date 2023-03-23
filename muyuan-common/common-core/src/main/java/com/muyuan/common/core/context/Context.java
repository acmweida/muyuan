package com.muyuan.common.core.context;

import com.muyuan.common.core.common.OptProvider;
import com.muyuan.common.valueobject.Opt;

public class Context {

    public static Opt currentOpt() {
       return ApplicationContextHandler.getContext().getBean(OptProvider.class).currentOpt();
    }
}
