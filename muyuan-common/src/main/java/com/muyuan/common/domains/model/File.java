package com.muyuan.common.domains.model;

import lombok.Data;

import java.util.Date;

@Data
public class File {

    private long id;

    /**
     * 文件大小 单位：bit
     */
    private long size;

    private String name;

    private String url;

    private String suffix;

    private Date upTime;

    private long upUser;

    /**
     * 状态 0-正常 1-删除
     */
    private boolean delete;

    /**
     * 审核状态 0-待审核 1-审核中 2-审核完成
     */
    private short verifyStatus;

    /**
     * 审核结果 0-审核通过 1-涉嫌违规 。。。
     */
    private short verifyResult;


}
