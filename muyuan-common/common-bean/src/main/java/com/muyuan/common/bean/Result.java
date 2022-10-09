package com.muyuan.common.bean;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 155793214856l;

    private int code;

    private String msg;

    private T data ;

    private Date optionTime = DateTime.now().toDate();

    private String responseId = UUID.randomUUID().toString();

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
