package com.muyuan.goods.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.goods.api.dto.GoodsDTO;
import com.muyuan.goods.api.dto.GoodsQueryRequest;
import com.muyuan.goods.api.GoodsServiceApi;
import com.muyuan.goods.domains.model.entity.goods.Goods;
import com.muyuan.goods.domains.service.GoodsService;
import com.muyuan.goods.face.dto.GoodsQueryCommond;
import com.muyuan.goods.face.dto.mapper.GoodsMapperImpl;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;


/**
 * @ClassName GoodsInterfaceImpl
 * Description 商品服务接口实现
 * @Author 2456910384
 * @Date 2022/8/25 15:42
 * @Version 1.0
 */
@DubboService(group = ServiceTypeConst.GOODS, version = "1.0"
        , interfaceClass = GoodsServiceApi.class
)
@AllArgsConstructor
public class GoodsServiceApiImpl implements GoodsServiceApi {

    private GoodsService goodsService;

    @Override
    public Page<GoodsDTO> page(GoodsQueryRequest request) {
        GoodsQueryCommond goodsQueryCommond = new GoodsMapperImpl().requestToCommend(request);
        Page<Goods> page =  goodsService.page(goodsQueryCommond);

        return null;
    }
}
