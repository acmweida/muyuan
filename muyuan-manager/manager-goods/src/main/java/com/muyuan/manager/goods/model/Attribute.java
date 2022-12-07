package com.muyuan.manager.goods.model;

import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.goods.repo.AttributeRepo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.util.Date;


/**
 * 商品类目属性对象 t_attribute
 *
 * @author ${author}
 * @date 2022-06-23T10:46:02.101+08:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {

    /**  */
    private Long id;

    private Long parentId;

    /**
     * 页面展示类型
     */
    private int htmlType;

    /** 属性名称 */
    private String name;

    /** 商品分类Code */
    private Long categoryCode;

    /** 属性编码 */
    private String code;

    /** 属性类型 转换为二进制 1:公共属性 10:销售属性 100:关键属性 1000:非关键属性 type值为类型的和 */
    private Integer type;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /**  */
    private Long createBy;

    /**  */
    private String creator;

    /**  */
    private Long updateBy;

    /**  */
    private String updater;

    /**
     * 输入类型
     */
    private Integer inputType;

    private Integer status;

    public Boolean isType(int type) {
        return ObjectUtils.isNotEmpty(this.type) && (this.type & type) > 0;
    }

    public void init() {
        createTime = DateTime.now().toDate();
        creator = SecurityUtils.getUsername();
        createBy = SecurityUtils.getUserId();
    }

    private void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void save(AttributeRepo attributeRepo) {
        Assert.notNull(attributeRepo, "repo is null");
        FunctionUtil.of(id)
                .ifThen(
                        () -> attributeRepo.insert(this),
                        id -> {
                            update();
                            attributeRepo.update(this);
                        }
                );
    }

}
