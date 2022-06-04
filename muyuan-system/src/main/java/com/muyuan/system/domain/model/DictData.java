package com.muyuan.system.domain.model;

import com.muyuan.common.mybatis.id.AutoIncrement;
import lombok.Data;

import java.util.Date;

/**
 * 字典数据
 */

/**
 *   id          bigint auto_increment comment '字典编码'
 *         primary key,
 *     sort        int(4)       default 0                 null comment '字典排序',
 *     label       varchar(100) default ''                null comment '字典标签',
 *     value       varchar(100) default ''                null comment '字典键值',
 *     type        varchar(100) default ''                null comment '字典类型',
 *     css_class   varchar(100)                           null comment '样式属性（其他样式扩展）',
 *     list_class  varchar(100)                           null comment '表格回显样式',
 *     def   tinyint(1)         default 0               null comment '是否默认（1是 0否）',
 *     status      tinyint(1)   default 0                 null comment '状态（0正常 1停用）',
 *     create_by   varchar(64)  default ''                null comment '创建者',
 *     create_time timestamp    default CURRENT_TIMESTAMP null,
 *     update_by   varchar(64)  default ''                null comment '更新者',
 *     update_time timestamp                              null on update CURRENT_TIMESTAMP,
 *     remark      varchar(500)                           null comment '备注'
 */
@Data
public class DictData {

    @AutoIncrement
    private Long id;

    private int orderNum;

    private String label;

    private String value;

    private String type;

    private String cssClass;

    private String listClass;

    private int def;

    private int status;

    private Long createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    public DictData() {
    }

    public DictData(String label, String value, String type) {
        this.label = label;
        this.value = value;
        this.type = type;
    }
}
