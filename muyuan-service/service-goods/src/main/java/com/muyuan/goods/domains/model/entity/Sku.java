package com.muyuan.goods.domains.model.entity;

import com.muyuan.common.core.global.IdGenerator;
import com.muyuan.goods.domains.model.valueobject.SkuId;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

@Data
public class Sku {

    private SkuId id;

    private Long goodsId;

    /**
     * 售价
     */
    private Double price;

    private String pic;

    /**
     * 锁定库存
     */
    private Integer stockLock;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 库存
     */
    private Integer stock;

    private String context;

    private Date createTime;

    private Date updateTime;

    public void initInstance(IdGenerator idGenerator) {
        initInstance(DateTime.now().toDate(), idGenerator);
    }

    public void initInstance(Date createTime, IdGenerator idGenerator) {
        this.createTime = createTime;
        this.stockLock = 0;
        setId(new SkuId(idGenerator.next()));
    }

    private void update() {
        updateTime = DateTime.now().toDate();
    }

    public void setId(SkuId id) {
        this.id = id;
    }
}
