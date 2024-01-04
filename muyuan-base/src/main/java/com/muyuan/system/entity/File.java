package com.muyuan.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class File extends BaseDO {

    @TableId(type = IdType.AUTO)
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
