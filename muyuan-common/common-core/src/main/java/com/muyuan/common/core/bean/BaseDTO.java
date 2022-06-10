package com.muyuan.common.core.bean;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;

/**
 * @ClassName BaseDTO
 * Description BaseDTO
 * @Author 2456910384
 * @Date 2022/5/19 11:00
 * @Version 1.0
 */
@Data
public abstract class BaseDTO<T extends BaseDTO, E> implements Converter<T, E>,Paging {

    private int pageNum = 1;

    private int pageSize= 10;

    protected  E newEntity() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<E> clazz = (Class<E>) pt.getActualTypeArguments()[1];
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    };

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
