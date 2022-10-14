package com.muyuan.user.domain.model.valueobject;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @ClassName RoleID
 * Description RoleId
 * @Author 2456910384
 * @Date 2022/9/14 9:14
 * @Version 1.0
 */
@Getter
public class MenuID implements  Comparable<MenuID> {

    private Long value;

    public MenuID() {
    }

    public MenuID(Long value) {
        setValue(value);
    }

    public void setValue(Long value) {
        Assert.notNull(value,"MenuID is null");
        Assert.isTrue(0 < value,"MenuID must more than 0");
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuID menuID = (MenuID) o;
        return value.equals(menuID.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }



    @Override
    public int compareTo(MenuID o) {
        return  o == null ? 1 : value.compareTo(o.getValue());
    }
}
