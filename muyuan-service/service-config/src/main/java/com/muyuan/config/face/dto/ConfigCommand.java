package com.muyuan.config.face.dto;

import com.muyuan.common.bean.OptCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wd
 * @ClassName ConfigRequest
 * Description
 * @date 2022-11-30T10:41:23.089+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class ConfigCommand extends OptCommand {

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
     * 备注
     */
    private String remark;


}
