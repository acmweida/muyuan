package com.muyuan.user.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DeptDTO
 * Description DeptDTO 部门
 * @Author 2456910384
 * @Date 2022/5/13 10:35
 * @Version 1.0
 */
@Data
public class DeptDTO implements Serializable {

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

    private String creator;

    private Date createTime;

    private Long updateBy;

    private String updater;

    private Date updateTime;

}
