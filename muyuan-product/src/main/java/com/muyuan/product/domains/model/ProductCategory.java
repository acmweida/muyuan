package com.muyuan.product.domains.model;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.id.Id;
import com.muyuan.common.web.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Optional;

/**
 * 商品分类
 */
@AllArgsConstructor
@Data
@Id(useGeneratedKeys = true)
@Builder
public class ProductCategory {

    private Long id;

    /**
     * 分类编码
     */
    private Long code;

    /**
     * 父分类ID
     */
    private Long parentId;

    private String name;

    private String logo;

    private Integer level;

    private Integer productCount;

    /**
     * 子节点数量
     */
    private Integer subCount;

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

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public boolean leaf() {
        return GlobalConst.TRUE.equals(leaf);
    }


    public void init() {
        status = "1";
        productCount = 0;
        level = 1;
        leaf = GlobalConst.TRUE;
        subCount = 0;
        createTime = DateTime.now().toDate();
        creator = SecurityUtils.getUsername();
        createBy = SecurityUtils.getUserId();
    }

    public void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void initRoot(long code) {
        parentId = null;
        level = 1;
        this.code = code;
        ancestors = String.valueOf(id);
    }

    public void linkParent(ProductCategory parent) {
        Assert.notNull(id, "id can not be null!");

        Optional.ofNullable(parent)
                .ifPresent(p -> {
                    parentId = p.id;
                    level = p.level + 1;
                    status = p.status;
                    ancestors = StringUtils.join(p.ancestors, ",", id);
                    p.leaf = GlobalConst.FALSE;
                    p.subCount += 1;
                    p.update();
                    newCode(parent);
                });
    }

    private void newCode(ProductCategory parent) {
        Assert.isTrue(level > 1,"level must grant than 2");
        if (ObjectUtils.isNotEmpty(parent)) {
            switch (level) {
                case 2:
                    // 父节点code * 1000 + subCount
                    code = code * 10000 + parent.subCount;
                    break;
                case 3:
                    // 父节点 code * 100000 + subCoount
                    code = code * 100000 + parent.subCount;
            }
        }
    }

}
