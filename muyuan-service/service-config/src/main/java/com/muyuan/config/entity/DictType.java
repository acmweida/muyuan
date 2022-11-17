package com.muyuan.config.entity;

import lombok.Data;

import java.util.Date;

/**
 * 字典类型
 */
@Data
public class DictType {

    @Data
    static public class Identify {

        private Long id;

        private String type;

        public Identify(Long id, String type) {
            this.id = id;
            this.type = type;
        }

        public Identify(String type) {
            this.type = type;
        }
    }


    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    private int status;

    private Date createTime;

    private Long createBy;

    private Date updateTime;

    private Long updateBy;

    private String remark;

}
