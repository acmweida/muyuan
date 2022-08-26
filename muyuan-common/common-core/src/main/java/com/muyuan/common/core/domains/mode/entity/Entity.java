package com.muyuan.common.core.domains.mode.entity;

import com.muyuan.common.core.domains.Identifiable;
import com.muyuan.common.core.domains.Identifier;

/**
 * @ClassName Entity
 * Description Entity
 * @Author 2456910384
 * @Date 2022/8/26 10:11
 * @Version 1.0
 */
public interface Entity<ID extends Identifier> extends Identifiable<ID> {
}
