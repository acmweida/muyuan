package com.muyuan.user.domain.model.valueobject;

import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @ClassName RoleCode
 * Description
 * @Author 2456910384
 * @Date 2022/9/14 10:09
 * @Version 1.0
 */
@Getter
public class RoleCode {

    private String value;

    public RoleCode(String value) {
        setValue(value);
    }

    public void setValue(String value) {
        Assert.notNull(value,"RoleCode username is null");
        Assert.isTrue(value.length() > 0,"RoleCode length must more than 0");
        this.value = value;
    }
}
