package com.muyuan.user.infrastructure.repo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Data;

/**
 * @ClassName RoleDO
 * Description RoleDO
 * @Author 2456910384
 * @Date 2022/9/15 14:16
 * @Version 1.0
 */
@Data
@TableName("t_role")
public class RoleDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    private String orderNum;

    private Integer platformType;

    /**
     * 状态 0-正常 1-停用
     */
    private String status;

}
