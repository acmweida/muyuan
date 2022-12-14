package com.muyuan.config.face.dto;

import com.muyuan.common.bean.OptCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DictTypeCommand extends OptCommand {

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
