package com.muyuan.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName OperLogParams
 * Description 操作日志记录实体
 * @Author ${author}
 * @Date  2022-12-15T15:27:12.638+08:00
 * @Version 1.0
 */
@Schema(name = "操作日志记录")
@Data
public class OperLogParams {

    /**
     * 校验分组
     */
    public interface Add {

    }

    public interface Update {

    }

        /** 日志主键 */
    @Schema(name = "id")
    @NotNull(message = "日志主键不能为空", groups ={Update.class})
    private Long id;
        /** 模块标题 */
    @Schema(name = "title")
    @NotBlank(message = "模块标题不能为空", groups ={Add.class, Update.class})
    private String title;
        /** 业务类型（0其它 1新增 2修改 3删除） */
    @Schema(name = "businessType")
    @NotNull(message = "业务类型（0其它 1新增 2修改 3删除）不能为空", groups ={Add.class, Update.class})
    private Integer businessType;
        /** 方法名称 */
    @Schema(name = "method")
    @NotBlank(message = "方法名称不能为空", groups ={Add.class, Update.class})
    private String method;
        /** 请求方式 */
    @Schema(name = "requestMethod")
    @NotBlank(message = "请求方式不能为空", groups ={Add.class, Update.class})
    private String requestMethod;
        /** 操作类别（0其它 1后台用户 2手机端用户） */
    @Schema(name = "operatorType")
    @NotNull(message = "操作类别（0其它 1后台用户 2手机端用户）不能为空", groups ={Add.class, Update.class})
    private Integer operatorType;
        /** 操作人员 */
    @Schema(name = "operName")
    @NotBlank(message = "操作人员不能为空", groups ={Add.class, Update.class})
    private String operName;
        /** 部门名称 */
    @Schema(name = "deptName")
    @NotBlank(message = "部门名称不能为空", groups ={Add.class, Update.class})
    private String deptName;
        /** 请求URL */
    @Schema(name = "operUrl")
    @NotBlank(message = "请求URL不能为空", groups ={Add.class, Update.class})
    private String operUrl;
        /** 主机地址 */
    @Schema(name = "operIp")
    @NotBlank(message = "主机地址不能为空", groups ={Add.class, Update.class})
    private String operIp;
        /** 操作地点 */
    @Schema(name = "operLocation")
    @NotBlank(message = "操作地点不能为空", groups ={Add.class, Update.class})
    private String operLocation;
        /** 请求参数 */
    @Schema(name = "operParam")
    @NotBlank(message = "请求参数不能为空", groups ={Add.class, Update.class})
    private String operParam;
        /** 返回参数 */
    @Schema(name = "jsonResult")
    @NotBlank(message = "返回参数不能为空", groups ={Add.class, Update.class})
    private String jsonResult;
        /** 操作状态（0正常 1异常） */
    @Schema(name = "status")
    @NotNull(message = "操作状态（0正常 1异常）不能为空", groups ={Add.class, Update.class})
    private Integer status;
        /** 错误消息 */
    @Schema(name = "errorMsg")
    @NotBlank(message = "错误消息不能为空", groups ={Add.class, Update.class})
    private String errorMsg;
        /** 操作时间 */
    @Schema(name = "operTime")
    private Date operTime;

}
