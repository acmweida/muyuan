package com.muyuan.goods.domains.model.entity;

import com.muyuan.common.valueobject.Opt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Brand {

    /**  */
    private Long id;

    /** 品牌名称 */
    private String name;

    /** 图标 */
    private String logo;

    /** 排序 */
    private Integer orderNum;

    /** 英文名称 */
    private String englishName;

    /** 状态  0-上架 1-下架 2-删除 3-禁用 */
    private Integer status;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 更新人 */
    private String updater;

    /** 创建人 */
    private String creator;

    /** 更吓人ID */
    private Long updateBy;

    /** 创建人ID */
    private Long createBy;

    /** 审核状态  1-审核中  0-审核通过 2-审核失败 */
    private Integer auditStatus;


    public Brand() {
    }

    public void init(Opt opt) {
        status = 3;
        auditStatus = 1;
        createTime = DateTime.now().toDate();
        this.createBy = opt.getId();
        this.creator = opt.getName();
    }

    private void update(Opt opt) {
        updateTime = DateTime.now().toDate();
        this.updateBy = opt.getId();
        this.updater = opt.getName();
    }

//    public void save(BrandRepo brandRepo,Opt opt) {
//        Assert.notNull(brandRepo, "repo is null");
//        FunctionUtil.of(id)
//                .ifThen(
//                        () -> brandRepo.insert(this),
//                        id -> {
//                            update(opt);
//                            brandRepo.update(this);
//                        }
//                );
//    }
//
//    public void update(BrandRepo brandRepo) {
//        brandRepo.update(this);
//    }
//
//    public void update(Opt opt, BrandRepo brandRepo, String... column) {
//        Assert.notNull(brandRepo, "repo is null");
//        Assert.notNull(id, "id is null");
//        update(opt);
//        brandRepo.update(this,ArrayUtils.addAll(column, "updateTime", "updateBy", "updater"));
//    }
//
//    public void audit(Opt opt,BrandRepo brandRepo) {
//        update(opt,brandRepo,"auditStatus");
//    }
}
