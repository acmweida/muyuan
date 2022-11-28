package com.muyuan.user.domain.model.entity;

import com.muyuan.common.core.util.StrUtil;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @ClassName DeptDTO
 * Description DeptDTO 部门
 * @Author 2456910384
 * @Date 2022/5/13 10:35
 * @Version 1.0
 */
@Data
public class Dept {

    @Data
    static public class Identify {

        private Long id;

        private Long parentId;

        private String name;


        public Identify(Long id, String name, Long parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }

        public Identify(String name, Long parentId) {
            this.name = name;
            this.parentId = parentId;
        }
    }

    private static final long serialVersionUID = 1457932248501l;

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

    private Long createBy;

    private Long createById;

    private Date createTime;

    private String updateBy;

    private Long updateById;

    private Date updateTime;

    private Dept parent;

    public void rebuildAncestors() {
        Assert.notNull(getId(),"id 不能为空");
        Assert.notNull(getParent(),"parent Dept 不能为空");
        setAncestors(StrUtil.format("{}.{}",parent.getAncestors(),getId()));
    }

}
