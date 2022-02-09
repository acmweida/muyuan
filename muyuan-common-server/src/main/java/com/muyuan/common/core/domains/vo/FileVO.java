package com.muyuan.common.core.domains.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "文件上传返回")
public class FileVO {

    private String url;
}
