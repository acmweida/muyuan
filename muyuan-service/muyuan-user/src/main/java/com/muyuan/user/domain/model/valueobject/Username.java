package com.muyuan.user.domain.model.valueobject;

import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @ClassName OperatorUsername
 * Description
 * @Author 2456910384
 * @Date 2022/9/14 10:09
 * @Version 1.0
 */
@Getter
public class Username {

    private String value;

    public Username(String value) {
        setValue(value);
    }

    public void setValue(String value) {
        Assert.notNull(value,"Operator username is null");
        Assert.isTrue(value.length() > 0,"Operator username must more than 0");
        this.value = value;
    }
}
