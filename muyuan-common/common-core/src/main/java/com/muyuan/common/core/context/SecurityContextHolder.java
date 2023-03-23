package com.muyuan.common.core.context;

import com.muyuan.common.core.util.Convert;
import com.muyuan.common.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取当前线程变量中的 用户id、用户名称、Token等信息
 * 注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 *
 * @author
 */
public class SecurityContextHolder {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        Map<String, Object> map = THREAD_LOCAL.get();
        map.put(key, value == null ? StrUtil.EMPTY : value);
    }

    public static String get(String key) {
        Map<String, Object> map = THREAD_LOCAL.get();
        return Convert.toStr(map.getOrDefault(key, StrUtil.EMPTY));
    }

    public static <T> T get(String key, Class<T> clazz) {
        Map<String, Object> map = THREAD_LOCAL.get();
        return StrUtil.cast(map.getOrDefault(key, null));
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
