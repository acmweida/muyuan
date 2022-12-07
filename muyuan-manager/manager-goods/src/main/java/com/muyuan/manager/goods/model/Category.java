package com.muyuan.manager.goods.model;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.mybatis.id.Id;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.goods.repo.CategoryRepo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 商品分类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Id(useGeneratedKeys = true)
@Builder
public class Category {

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

    private Integer goodsCount;

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

    private List<Attribute> attributes;

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
        goodsCount = 0;
        level = 1;
        leaf = GlobalConst.TRUE;
        subCount = 0;
        createTime = DateTime.now().toDate();
        creator = SecurityUtils.getUsername();
        createBy = SecurityUtils.getUserId();
    }

    private void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void initRoot(int rootCount) {
        parentId = null;
        level = 1;
        newCode(null, rootCount + 1);
        ancestors = String.valueOf(id);
    }

    public void linkParent(Category parent, int count) {
        Assert.notNull(id, "id can not be null!");

        Optional.ofNullable(parent)
                .ifPresent(p -> {
                    parentId = p.id;
                    level = p.level + 1;
                    status = p.status;
                    ancestors = StringUtils.join(p.ancestors, ",", id);
                    p.leaf = GlobalConst.FALSE;
                    p.subCount += 1;
                    newCode(parent, count + 1);
                });
    }

    private void newCode(Category parent, long index) {
        if (ObjectUtils.isNotEmpty(parent)) {
            switch (level) {
                case 2:
                    // 父节点code  + (1000+index) << 14
                    code = parent.code + ((1000 + index) << 14);
                    break;
                case 3:
                    // 父节点 code + index
                    code = parent.code + index;
            }
        } else {
            this.code =   index << 30;
        }
    }

    public void save(CategoryRepo categoryRepo) {
        Assert.notNull(categoryRepo, "repo is null");
        FunctionUtil.of(id)
                .ifThen(
                        () -> categoryRepo.insert(this),
                        id -> {
                            update();
                            categoryRepo.update(this);
                        }
                );
    }

    public void update(CategoryRepo categoryRepo, String... column) {
        Assert.notNull(categoryRepo, "repo is null");
        Assert.notNull(id, "id is null");
        update();
        categoryRepo.update(this,column);
    }

    public boolean hasChildren() {
        return !leaf();
    }


}
