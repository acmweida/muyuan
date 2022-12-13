package com.muyuan.system.entity;

import lombok.Data;

import java.util.Date;

@Data
public class File {

    private Long id;

    /**
     * 文件大小 单位：bit
     */
    private Long size;

    private String name;

    private String url;

    /**
     * FastDFS路径
     */
    private String filePath;

    private String suffix;

    private Date createTime;

    private Long createBy;

    /**
     * 状态 0-正常 1-删除
     */
    private String status;

    /**
     * 模块
     */
    private String module;

    /**
     * 功能
     */
    private String function;
}
