package com.muyuan.user.domain.model.valueobject;

import com.muyuan.common.core.domains.Identifier;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @ClassName OperatorID
 * Description UserID
 * @Author 2456910384
 * @Date 2022/9/14 9:14
 * @Version 1.0
 */
@Getter
public class UserID implements Identifier {

    private Long value;

    public UserID() {
    }

    public UserID(Long value) {
        setValue(value);
    }

    public void setValue(Long value) {
        Assert.notNull(value,"UserID is null");
        Assert.isTrue(0 < value,"OperatorId must more than 0");
        this.value = value;
    }
}
