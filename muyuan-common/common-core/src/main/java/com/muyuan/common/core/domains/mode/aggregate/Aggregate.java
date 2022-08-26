package com.muyuan.common.core.domains.mode.aggregate;

import com.muyuan.common.core.domains.mode.entity.Entity;
import com.muyuan.common.core.domains.Identifier;

/**
 * @ClassName Aggregate 接口
 * Description 聚会
 * @Author 2456910384
 * @Date 2022/8/26 10:13
 * @Version 1.0
 */
public interface Aggregate<ID extends Identifier> extends Entity<ID> {
}
