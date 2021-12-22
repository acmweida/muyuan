package com.muyuan.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

@Data
@ApiModel
public class Result<T> {

    @ApiModelProperty(value = "响应码")
    private int code;

    @ApiModelProperty(value = "响应信息")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data ;

    @ApiModelProperty(value = "操作时间")
    private Date optionTime = DateTime.now().toDate();

    @ApiModelProperty(value = "响应吗")
    private String responseId = UUID.randomUUID().toString();

    public Result(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code,String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
