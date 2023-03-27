package com.muyuan.config.repo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Data;


/**
 * 参数配置对象 t_config
 *
 * @author wd
 * @date 2022-11-30T10:41:23.089+08:00
 */
@Data
@TableName("t_config")
public class ConfigDO extends BaseDO {

    /** 参数主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 参数名称 */
    private String name;

    /** 参数键名 */
    private String configKey;

    /** 参数键值 */
    private String configValue;

    /** 系统内置（Y是 N否） */
    private Integer type;

    /** 备注 */
    private String remark;




}
