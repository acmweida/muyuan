package com.muyuan.common.core.util;

import java.util.Collection;

/**
 * @ClassName CollectionAssert
 * Description 集合
 * @Author 2456910384
 * @Date 2021/10/11 16:29
 * @Version 1.0
 */
public class CollectionAssert {

    /**
     * 集合不包含某个元素
     * @param collection
     * @param element
     * @param message
     */
    public static void notContain(Collection collection , Object element,String message) {
        try {
            contain(collection,element,message);
        } catch (IllegalArgumentException e) {
            return;
        }
        throw new IllegalArgumentException(message);
    }

    /**
     * 集合包含某个元素
     * @param collection
     * @param element
     * @param message
     */
    public static void contain(Collection collection,Object element,String message) {
        for (Object el : collection) {
            if (el == element || (null != el && el.equals(element))) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * 集合不包含null
     * @param collection
     * @param message
     */
    public static void notContainNull(Collection collection,String message) {
        notContain(collection,null,message);
    }
}
