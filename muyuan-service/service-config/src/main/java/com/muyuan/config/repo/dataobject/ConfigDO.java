package com.muyuan.config.repo.dataobject;

import lombok.Data;

import java.util.Date;


/**
 * 参数配置对象 t_config
 *
 * @author ${author}
 * @date 2022-11-29T16:27:55.007+08:00
 */
@Data
public class ConfigDO  {

    /** 参数主键 */
    private Long id;

    /** 参数名称 */
    private String configName;

    /** 参数键名 */
    private String configKey;

    /** 参数键值 */
    private String configValue;

    /** 系统内置（Y是 N否） */
    private String configType;

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


}
