package com.muyuan.config.api.dto;

import com.muyuan.common.bean.OptRequest;
import com.muyuan.common.valueobject.Opt;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DictTypeRequest extends OptRequest implements Serializable {

    @Builder
    public DictTypeRequest(Opt opt, String name, String type, int status, String remark, Long id) {
        super(opt);
        this.name = name;
        this.type = type;
        this.status = status;
        this.remark = remark;
        this.id = id;
    }

    private static final long serialVersionUID = 1457932158568l;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    private int status;

    private String remark;

    private Long id;
}
