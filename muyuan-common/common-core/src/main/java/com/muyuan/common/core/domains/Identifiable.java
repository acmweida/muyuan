package com.muyuan.common.core.domains;

/**
 * @ClassName Identifiable 接口
 * Description 主键
 * @Author 2456910384
 * @Date 2022/8/26 9:32
 * @Version 1.0
 */
public interface Identifiable<ID extends Identifier> {

    ID getId();
}
