package com.muyuan.common.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.muyuan.common.core.context.Context;
import com.muyuan.common.redis.util.IdUtil;
import com.muyuan.common.valueobject.Opt;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段填充审计
 *
 * @author lili
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Opt opt = Context.currentOpt();
        if (opt != null) {
            this.setFieldValByName("createBy", opt.getId(), metaObject);
            this.setFieldValByName("creator", opt.getName(), metaObject);
        } else {
            this.setFieldValByName("createBy", "0", metaObject);
            this.setFieldValByName("creator", "SYSTEM", metaObject);
        }
        //有创建时间字段，切字段值为空
        if (metaObject.hasGetter("createTime")) {
            this.setFieldValByName("createTime", new Date(), metaObject);
        }
        //有值，则写入
        if (metaObject.hasGetter("deleteFlag")) {
            this.setFieldValByName("deleteFlag", false, metaObject);
        }
        if (metaObject.hasGetter("id")) {
            //如果已经配置id，则不再写入
            if (metaObject.getValue("id") == null) {
                assert opt != null;
                this.setFieldValByName("id", String.valueOf(IdUtil.createId(metaObject.getOriginalObject().getClass())), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        Opt opt = Context.currentOpt();
        if (opt != null) {
            this.setFieldValByName("updateBy", opt.getId(), metaObject);
            this.setFieldValByName("creator", opt.getName(), metaObject);
        }
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}

