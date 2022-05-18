package com.muyuan.system.domain.entity;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.system.domain.model.SysDept;
import lombok.Data;
import org.springframework.util.Assert;

/**
 * @ClassName SysDeptEntity
 * Description 部门 entity
 * @Author 2456910384
 * @Date 2022/5/18 14:34
 * @Version 1.0
 */
@Data
public class SysDeptEntity extends SysDept {

    private SysDept parent;

    public void rebuildAncestors() {
        Assert.notNull(getId(),"id 不能为空");
        Assert.notNull(getParent(),"parent Dept 不能为空");
        super.setAncestors(StrUtil.format("{}.{}",parent.getAncestors(),getId()));
    }
}
