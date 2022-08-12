package com.muyuan.system.domains.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysDeptVO
 * Description 部门VO
 * @Author 2456910384
 * @Date 2022/5/13 10:55
 * @Version 1.0
 */
@Data
public class SysDeptVO {

    private Long id;

    private String parentId;

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

    private List<SysDeptVO> children = new ArrayList<>(0);
}
