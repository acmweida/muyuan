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

    private String type;

    private String msg;

    private Optional<T> data = Optional.empty();

    private Date optionTime = DateTime.now().toDate();

    public Result(int code, String type, String msg) {
        this.code = code;
        this.type = type;
        this.msg = msg;
    }

    public Result(int code, String type, String msg, T data) {
        this.code = code;
        this.type = type;
        this.msg = msg;
        this.data = Optional.of(data);
    }
}
