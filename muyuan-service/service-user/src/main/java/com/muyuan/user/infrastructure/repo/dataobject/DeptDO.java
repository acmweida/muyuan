package com.muyuan.user.infrastructure.repo.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName DeptDTO
 * Description DeptDTO 部门
 * @Author 2456910384
 * @Date 2022/5/13 10:35
 * @Version 1.0
 */
@Data
public class DeptDO {

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

}
