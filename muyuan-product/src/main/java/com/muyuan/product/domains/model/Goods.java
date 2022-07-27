package com.muyuan.product.domains.model;

import com.muyuan.common.core.util.FunctionUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.product.domains.repo.GoodsRepo;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 商品基本信息
 */
@Data
public class Goods {

    private Long id;

    /**
     * todo:物流模板
     */
    private Long wuliouModel;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 删除 0-否 1-是
     */
    private String delete;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 分类id
     */
    private Long categoryCode;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 是否上架
     */
    private String publish;

    /***
     * 详情页面
     */
    private String detailUrl;

    /**
     * 主图
     */
    private String mainPictureUrl;

    /**
     * 商品标签
     */
    private String tags;


    private List<Sku> skus;

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

    private void update() {
        updateTime = DateTime.now().toDate();
        updateBy = SecurityUtils.getUserId();
        updater = SecurityUtils.getUsername();
    }

    public void save(GoodsRepo goodsRepo) {
        Assert.notNull(goodsRepo, "repo is null");
        FunctionUtil.of(id)
                .ifThen(
                        () -> goodsRepo.insert(this),
                        id -> {
                            update();
                            goodsRepo.update(this);
                        }
                );
    }

}
