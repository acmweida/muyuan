package com.muyuan.config.api.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wd
 * @ClassName ConfigQueryRequest
 * Description
 * @date 2022-11-30T10:41:23.089+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class ConfigQueryRequest extends PageDTO implements Serializable {


    @Builder
    public ConfigQueryRequest(Integer pageNum, Integer pageSize, Long id, String name, String configKey, String configValue, Integer type, String createBy, Date createTime, String updateBy, Date updateTime, String remark, Long creator, Long updater) {
        super(pageNum, pageSize);
        this.id = id;
        this.name = name;
        this.configKey = configKey;
        this.configValue = configValue;
        this.type = type;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
        this.creator = creator;
        this.updater = updater;
    }

    private static final long serialVersionUID = 1457932148568l;

    /**
     * 参数主键
     */
    private Long id;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    private Integer type;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * $column.columnComment
     */
    private Long creator;

    /**
     * $column.columnComment
     */
    private Long updater;

}
