package com.muyuan.product.domains.model;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.mybatis.id.Id;
import com.muyuan.common.web.util.SecurityUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * 商品分类
 */
@Data
@Id(useGeneratedKeys = true)
public class ProductCategory {

    private Long id;

    /**
     * 分类编码
     */
    private String code;

    /**
     * 父分类ID
     */
    private Long parentId;

    private String name;

    private String logo;

    private Integer level;

    private Integer productCount;

    /**
     * 状态 0-上架 1-下架 2-删除
     */
    private String status;

    private Integer orderNum;

    private String ancestors;

    private Date createTime;

    private String creator;

    private Long createBy;

    private String updater;

    private Long updateBy;

    private Date updateTime;

    /**
     * 是否叶子节点 0-是 1-否
     */
    private String leaf;

    public boolean isLeaf() {
        return GlobalConst.TRUE.equals(leaf);
    }

    public void init() {
        status = "1";
        productCount = 0;
        leaf = GlobalConst.TRUE;
        createTime = DateTime.now().toDate();
        creator = SecurityUtils.getUsername();
        createBy = SecurityUtils.getUserId();
    }

    public void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void clearParentId() {
        parentId = null;
        level = 1;
        ancestors = String.valueOf(id);
    }

    public void linkParent(ProductCategory parent) {
        Assert.notNull(id, "id can not be null!");
        FunctionUtil.of(parent)
                .ifThen(
                        () -> clearParentId(),
                        p -> {
                            parentId = p.id;
                            level = p.level + 1;
                            status = p.status;
                            ancestors = StringUtils.join(p.ancestors, ",", id);
                            p.leaf = GlobalConst.FALSE;
                        }
                );
    }
}
