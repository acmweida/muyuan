package com.muyuan.system.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "文件上传返回")
public class FileVO {

    private String url;

    private String name;

    private String id;
}
