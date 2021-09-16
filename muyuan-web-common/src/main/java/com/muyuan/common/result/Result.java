package com.muyuan.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Optional;

@Data
@ApiModel
public class Result<T> {

    private int code;

    private String msg;

    private String type;

    private T data ;

    private Date optionTime = DateTime.now().toDate();

    public Result(int code, String type,String msg) {
        this.code = code;
        this.msg = msg;
        this.type =type;
    }

    public Result(int code, String type, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.type =type;
        this.data = data;
    }
}
