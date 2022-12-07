package com.muyuan.common.valueobject;

import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @ClassName Opt
 * Description 操作人
 * @Author 2456910384
 * @Date 2022/12/7 16:42
 * @Version 1.0
 */
@Getter
public class Opt {

    private Long id;

    private String name;

    public Opt(Long id, String name) {
        Assert.notNull(id,"id is null");
        Assert.isTrue(0 < id,"id must more than 0");
        Assert.notNull(name,"name is null");
        Assert.isTrue(0 < name.length(),"name length is 0");
        this.id = id;
        this.name = name;
    }
}
