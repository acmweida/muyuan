package com.muyuan.common.core.domains.repo;

import com.muyuan.common.core.domains.Identifier;
import com.muyuan.common.core.domains.mode.aggregate.Aggregate;
import jakarta.validation.constraints.NotNull;

/**
 * @ClassName Repository 接口
 * Description Repository
 * @Author 2456910384
 * @Date 2022/8/26 10:18
 * @Version 1.0
 */
public interface Repository<T extends Aggregate<ID>, ID extends Identifier>  {

    /**
     * 通过ID寻找Aggregate。
     * 找到的Aggregate自动是可追踪的
     */
    T find(@NotNull ID id);

    /**
     * 将一个Aggregate从Repository移除
     * 操作后的aggregate对象自动取消追踪
     */
    void remove(@NotNull T aggregate);

    /**
     * 保存一个Aggregate
     * 保存后自动重置追踪条件
     */
    void save(@NotNull T aggregate);


}
