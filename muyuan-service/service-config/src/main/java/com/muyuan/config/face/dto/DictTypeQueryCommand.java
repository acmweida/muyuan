package com.muyuan.config.face.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;


/**
 * @ClassName DictDataDTO
 * Description 字典类型DTO
 * @Author 2456910384
 * @Date 2022/3/30 16:47
 * @Version 1.0
 */
@Data
public class DictTypeQueryCommand extends PageDTO {

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

}
