package com.muyuan.common.core.bean;

import org.springframework.beans.BeanUtils;

/**
 * @ClassName BaseDTO
 * Description BaseDTO
 * @Author 2456910384
 * @Date 2022/5/19 11:00
 * @Version 1.0
 */
public abstract class BaseDTO<T extends BaseDTO, E> implements Converter<T, E> {

    protected abstract E newEntity();

    @Override
    public E convert(T bean) {
        E entity = newEntity();
        BeanUtils.copyProperties(bean, entity);
        return entity;
    }

    public E convert() {
        return convert((T) this);
    }

}
