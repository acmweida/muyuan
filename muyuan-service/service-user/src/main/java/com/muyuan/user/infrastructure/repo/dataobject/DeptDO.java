package com.muyuan.user.infrastructure.repo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Data;

/**
 * @ClassName DeptDTO
 * Description DeptDTO 部门
 * @Author 2456910384
 * @Date 2022/5/13 10:35
 * @Version 1.0
 */
@Data
@TableName("t_dept")
public class DeptDO extends BaseDO {

    @TableId(type = IdType.AUTO)
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

}
