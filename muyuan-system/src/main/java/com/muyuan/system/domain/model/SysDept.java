package com.muyuan.system.domain.model;

import com.muyuan.common.core.util.StrUtil;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @ClassName SysDept
 * Description SysDept 部门
 * @Author 2456910384
 * @Date 2022/5/13 10:35
 * @Version 1.0
 */
@Data
public class SysDept {

    private Long id;

    private Long parentId;

    private String ancestors;

    private String name;

    private Integer orderNum;

    private String leader;

    private String phone;

    private String email;

    private String status;

    private String delFlag;

    private String createBy;

    private Long createById;

    private Date createTime;

    private String updateBy;

    private Long updateById;

    private Date updateTime;

    public SysDept() {
    }

    public SysDept(Long parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

    private SysDept parent;

    public void rebuildAncestors() {
        Assert.notNull(getId(),"id 不能为空");
        Assert.notNull(getParent(),"parent Dept 不能为空");
        setAncestors(StrUtil.format("{}.{}",parent.getAncestors(),getId()));
    }
}
