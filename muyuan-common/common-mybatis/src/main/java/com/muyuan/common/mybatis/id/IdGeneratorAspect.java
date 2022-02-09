package com.muyuan.common.mybatis.id;

import com.muyuan.common.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;

@Component
@Order(-10)
@Aspect
@Slf4j
public class IdGeneratorAspect {

    @Autowired
    IdUtil idUtil;

    @Before("@annotation(idGenerator)")
    public void changeDataSource(JoinPoint point, IdGenerator idGenerator) throws Throwable {
        String idFieldName=idGenerator.fieldName();
        final Object[] args = point.getArgs();
        if (args.length != 1) {
            log.error("id generator method must only have one param");
        } else {
            Object entity = args[0];
            Class target = null;
            Method writeMethod = null;
            if (entity instanceof Collection || entity.getClass().isArray()) {
                Object[] entitys = null;
                if (entity instanceof Collection) {
                    entitys = ((Collection)entity).toArray();
                } else {
                    entitys = (Object[]) entity;
                }
                target = entitys[0].getClass();
                writeMethod = getIdWriterMethod(target,idFieldName);
                if (writeMethod == null) {
                    log.info("id generator not found writer method!");
                    return;
                }
                for (Object item : entitys ) {
                    writeMethod.invoke(item,idUtil.createId());
                }
            } else  {
                target = entity.getClass();
                writeMethod = getIdWriterMethod(target,idFieldName);
                writeMethod.invoke(entity,idUtil.createId());
            }
        }

    }

    public Method getIdWriterMethod(Class target,String idFieldName) {
        final PropertyDescriptor[] beanGetters = ReflectUtils.getBeanGetters(target);
        for (PropertyDescriptor propertyDescriptor : beanGetters) {
            if (propertyDescriptor.getName().equals(idFieldName)) {
                return propertyDescriptor.getWriteMethod();
            }
        }
        return null;
    }

}
