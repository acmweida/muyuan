package com.muyuan.common.util.string;

/**
 * @ClassName FormattingTuple
 * Description TODO
 * @Author 2456910384
 * @Date 2021/10/13 13:57
 * @Version 1.0
 */
public class FormattingTuple {
    static public org.slf4j.helpers.FormattingTuple NULL = new org.slf4j.helpers.FormattingTuple(null);

    private String message;
    private Throwable throwable;
    private Object[] argArray;

    public FormattingTuple(String message) {
        this(message, null, null);
    }

    public FormattingTuple(String message, Object[] argArray, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
        this.argArray = argArray;
    }

    public String getMessage() {
        return message;
    }

    public Object[] getArgArray() {
        return argArray;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
