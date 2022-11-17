package com.muyuan.config.entity;

import lombok.Data;

import java.util.Date;

/**
 * 字典数据
 */
@Data
public class DictData {

    @Data
    static public class Identify {

        private Long id;

        private String value;

        private String type;

        public Identify(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public Identify(Long id, String value, String type) {
            this.id = id;
            this.value = value;
            this.type = type;
        }
    }

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

    private Long updateBy;

    private Date updateTime;

    private String remark;

}
