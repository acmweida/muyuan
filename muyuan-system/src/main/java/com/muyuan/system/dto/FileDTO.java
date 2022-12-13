package com.muyuan.system.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName FileDTO
 * Description 文件上传对象
 * @Author 2456910384
 * @Date 2022/6/15 10:59
 * @Version 1.0
 */
@Data
public class FileDTO {

    /**
     * 模块名称
     */
    @NotBlank(message = "文件上传模块名称不能为空")
    private String module;

    /**
     * 功能名称
     */
    @NotBlank(message = "文件上传功能名称不能为空")
    private String function;

    /**
     * 上传文件
     */
    @NotEmpty(message = "文件没有上传")
    private MultipartFile file;
}
