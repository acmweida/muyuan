package com.muyuan.config.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DataTypeDTO
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 9:55
 * @Version 1.0
 */
@Data
public class DictTypeDTO implements Serializable {

    private static final long serialVersionUID = 1457932158668l;

    private Long id;

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

    private Date createTime;

    private Long createBy;

    private Date updateTime;

    private Long updateBy;

    private String remark;

    private List<DictDataDTO> dictDataDOList;
}
