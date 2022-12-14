package com.muyuan.config.face.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wd
 * @ClassName ConfigQueryRequest
 * Description
 * @date 2022-11-30T10:41:23.089+08:00
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigQueryCommand extends PageDTO {

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
