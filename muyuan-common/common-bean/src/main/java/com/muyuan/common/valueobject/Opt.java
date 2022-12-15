package com.muyuan.common.valueobject;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @ClassName Opt
 * Description 操作人
 * @Author 2456910384
 * @Date 2022/12/7 16:42
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Opt implements Serializable {

    private static final long serialVersionUID = 1481444072623268085L;

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
