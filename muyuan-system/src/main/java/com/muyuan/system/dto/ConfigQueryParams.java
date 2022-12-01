package com.muyuan.system.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;

import java.util.Date;


/**
 * 参数配置查询参数
 *
 * @author wd
 * @date 2022-11-30T10:41:23.089+08:00
 */
@Data
public class ConfigQueryParams extends PageDTO {

    /** 参数主键 */
    private Long id;

    /** 参数名称 */
    private String name;

    /** 参数键名 */
    private String configKey;

    /** 参数键值 */
    private String configValue;

    /** 系统内置（Y是 N否） */
    private Integer type;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** $column.columnComment */
    private Long creator;

    /** $column.columnComment */
    private Long updater;


}
