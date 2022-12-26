package com.muyuan.goods.domains.model.entity;

import com.muyuan.common.valueobject.Opt;
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

        private Long parentId;

        private String name;

        public Identify(Long parentId, String name) {
            this.parentId = parentId;
            this.name = name;
        }

        public Identify(Long id, Long parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
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
    private Boolean leaf;

    public void init(Opt opt) {
        status = 1;
        level = 1;
        leaf = true;
        subCount = 0;
        createTime = DateTime.now().toDate();
        creator = opt.getName();
        createBy = opt.getId();
    }

    private void update(Opt opt) {
        updateTime = DateTime.now().toDate();
        updateBy = opt.getId();
        updater = opt.getName();
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
                    p.leaf = false;
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

}
