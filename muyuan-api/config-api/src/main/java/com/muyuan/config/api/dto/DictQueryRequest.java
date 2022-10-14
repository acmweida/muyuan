package com.muyuan.config.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName DictQueryRequest
 * Description 字典查询请求
 * @Author 2456910384
 * @Date 2022/10/14 10:19
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictQueryRequest implements Serializable {

    private static final long serialVersionUID = 1457932158568l;

    private String dictTypeName;
}
