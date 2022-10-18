package com.muyuan.user.domain.model.valueobject;

import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @ClassName RoleID
 * Description RoleId
 * @Author 2456910384
 * @Date 2022/9/14 9:14
 * @Version 1.0
 */
@Getter
public class RoleID {

    private Long value;

    public RoleID() {
    }

    public RoleID(Long value) {
        setValue(value);
    }

    public void setValue(Long value) {
        Assert.notNull(value,"RoleID is null");
        Assert.isTrue(0 < value,"OperatorId must more than 0");
        this.value = value;
    }
}
