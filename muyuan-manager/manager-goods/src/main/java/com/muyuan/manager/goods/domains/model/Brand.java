package com.muyuan.manager.goods.domains.model;

import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.goods.domains.repo.BrandRepo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

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

    public void init() {
        status = 3;
        auditStatus = 1;
        createTime = DateTime.now().toDate();
        creator = SecurityUtils.getUsername();
        createBy = SecurityUtils.getUserId();
    }

    private void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void save(BrandRepo brandRepo) {
        Assert.notNull(brandRepo, "repo is null");
        FunctionUtil.of(id)
                .ifThen(
                        () -> brandRepo.insert(this),
                        id -> {
                            update();
                            brandRepo.update(this);
                        }
                );
    }

    public void update(BrandRepo brandRepo) {
        brandRepo.update(this);
    }

    public void update(BrandRepo brandRepo, String... column) {
        Assert.notNull(brandRepo, "repo is null");
        Assert.notNull(id, "id is null");
        update();
        brandRepo.update(this,ArrayUtils.addAll(column, "updateTime", "updateBy", "updater"));
    }

    public void audit(BrandRepo brandRepo) {
        update(brandRepo,"auditStatus");
    }
}
