package com.muyuan.common.bean;

/**
 * @ClassName Converter 接口
 * Description 类转换接口
 * @Author 2456910384
 * @Date 2022/5/19 9:11
 * @Version 1.0
 */
public interface Converter<F,M> {

     M convert(F bean);

}
