package com.muyuan.goods.face.interfaces;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.goods.api.dataobject.GoodsDO;
import com.muyuan.goods.api.service.GoodsServiceApi;
import com.muyuan.goods.api.to.GoodsTO;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

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
public class GoodsServiceApiImpl implements GoodsServiceApi {


    @Override
    public List<GoodsDO> page(GoodsTO goodsTO) {
        return null;
    }
}
