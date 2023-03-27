package com.muyuan.config.repo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Data;

/**
 * 字典类型
 */
@Data
@TableName("t_dict_type")
public class DictTypeDO  extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    private int status;

    private String remark;

    public DictTypeDO(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public DictTypeDO() {
    }
}
