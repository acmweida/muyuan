package com.muyuan.config.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.*;

import java.io.Serializable;

/**
 * @author wd
 * @ClassName ConfigRequest
 * Description
 * @date 2022-11-30T10:41:23.089+08:00
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class ConfigRequest extends OptRequest implements Serializable {

    @Builder
    public ConfigRequest(Opt opt, Long id, String name, String configKey, String configValue, Integer type, String remark) {
        super(opt);
        this.id = id;
        this.name = name;
        this.configKey = configKey;
        this.configValue = configValue;
        this.type = type;
        this.remark = remark;
    }

    private static final long serialVersionUID = 1357932148568l;

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
