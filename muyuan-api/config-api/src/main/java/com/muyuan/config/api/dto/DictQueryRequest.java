package com.muyuan.config.api.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DictQueryRequest
 * Description 字典查询请求
 * @Author 2456910384
 * @Date 2022/10/14 10:19
 * @Version 1.0
 */

@Data
public class DictQueryRequest extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1457932158568l;

    private String type;

    private String status;

    private String label;
}
