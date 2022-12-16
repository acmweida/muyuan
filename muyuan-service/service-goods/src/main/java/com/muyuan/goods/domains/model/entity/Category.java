package com.muyuan.goods.domains.model.entity;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.goods.domains.repo.CategoryRepo;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 商品分类对象 t_category
 *
 * @author ${author}
 * @date 2022-12-16T11:54:09.147+08:00
 */
@Data
public class Category {

    @Data
    static public class Identify {

        private Long id;

        public Identify(Long id) {
            this.id = id;
        }
    }

    /** 主键 */
    private Long id;

    /** 父类ID */
    private Long parentId;

    /** 分类名称 */
    private String name;

    /** 晨级 */
    private Integer level;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /** 产品编码 */
    private Long code;

    /** 层级路径 */
    private String ancestors;

    /** $column.columnComment */
    private String logo;

    /** $column.columnComment */
    private Integer productCount;

    /** 0-上架 1-下架 2-删除 */
    private Integer status;

    /** $column.columnComment */
    private Integer orderNum;

    /** 创建人ID */
    private Long createBy;

    /** 创建人 */
    private String creator;

    /** 更新人ID */
    private Long updateBy;

    /** 更新人
 */
    private String updater;

    /** 子节点数量 */
    private Integer subCount;

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
        status = 1;
        level = 1;
        leaf = GlobalConst.TRUE;
        subCount = 0;
        createTime = DateTime.now().toDate();
//        creator = SecurityUtils.getUsername();
//        createBy = SecurityUtils.getUserId();
    }

    private void update() {
        updateTime = DateTime.now().toDate();
//        updateBy = SecurityUtils.getUserId();
//        updater = SecurityUtils.getUsername();
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

//    public void save(CategoryRepo categoryRepo) {
//        Assert.notNull(categoryRepo, "repo is null");
//        FunctionUtil.of(id)
//                .ifThen(
//                        () -> categoryRepo.insert(this),
//                        id -> {
//                            update();
//                            categoryRepo.update(this);
//                        }
//                );
//    }

    public void update(CategoryRepo categoryRepo, String... column) {
        Assert.notNull(categoryRepo, "repo is null");
        Assert.notNull(id, "id is null");
        update();
//        categoryRepo.update(this,column);
    }

    public boolean hasChildren() {
        return !leaf();
    }



}
