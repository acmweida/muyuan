package com.muyuan.system.dto.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "文件上传返回")
public class FileVO {

    private String url;

    private String name;

    private String id;
}
