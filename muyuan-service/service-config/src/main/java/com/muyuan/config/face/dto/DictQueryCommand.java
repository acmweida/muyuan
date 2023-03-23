package com.muyuan.config.face.dto;

import com.muyuan.common.bean.PageDTO;
import lombok.Data;

/**
 * @ClassName DictQuerycommand
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 10:42
 * @Version 1.0
 */
@Data
public class DictQueryCommand extends PageDTO {

    private String type;

    private String status;

    private String label;
}
