package com.muyuan.common.core.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @ClassName FunctionUtil
 * Description 逻辑工具类
 * @Author 2456910384
 * @Date 2022/5/17 11:41
 * @Version 1.0
 */
public class FunctionUtil {

    public static Supplier getIfNullThen(Supplier exec, Supplier then) {
        return () -> {
            Object o = exec.get();
            if (o == null) {
                return then.get();
            }
            return o;
        };
    }

    public static Supplier getAndThen(Supplier exec, Consumer then) {
        return () -> {
            Object  o = exec.get();
            then.accept(o);
            return o;
        };
    }

    public static Supplier getIfNullThenRebuild(Supplier exec,Supplier rebuild,Consumer then) {
        return () -> {
            Object o = exec.get();
            if (o == null) {
              o = rebuild.get();
            }
            then.accept(o);
            return o;
        };
    }
}
