package com.muyuan.common.mybatis.util;

import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * @ClassName StatementUtil
 * Description StatementUtil
 * @Author 2456910384
 * @Date 2022/5/5 11:12
 * @Version 1.0
 */
public class StatementUtil {

    public static String getMapperName(Object v) {
        String mapperName = v.getClass().getName();
        Class  c = v.getClass();
        while (c.getName().contains(JDK_PROXY_NAME_PREFIX) || c.getName().contains(CGLIB_CLASS_SEPARATOR)) {
            if (c.getName().contains(JDK_PROXY_NAME_PREFIX)) {
                try {
                    mapperName = getJdkProxyMapperName(c,v);
                    c = Class.forName(mapperName);
                } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
                    e.printStackTrace();
                    return "";
                }
            } else if (c.getName().contains(CGLIB_CLASS_SEPARATOR)) {
                c = ClassUtils.getUserClass(c);
                mapperName = c.getName();
            }
        }
        return mapperName;
    }

    private static final String JDK_PROXY_NAME_PREFIX = "com.sun.proxy.$Proxy";

    private static final String CGLIB_CLASS_SEPARATOR = "$$";


    private static String getJdkProxyMapperName(Class c, Object proxy) throws IllegalAccessException, NoSuchFieldException {
        Field field = proxy.getClass().getSuperclass().getDeclaredField("h");
        field.setAccessible(true);

        Object o = field.get(proxy);
        Field mapperInterface = o.getClass().getDeclaredField("mapperInterface");

        mapperInterface.setAccessible(true);
        return ((Class) mapperInterface.get(o)).getName();
    }
}
