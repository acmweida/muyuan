package com.muyuan.goods.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.goods.api.GoodsInterface;
import com.muyuan.goods.api.dto.GoodsDTO;
import com.muyuan.goods.api.dto.GoodsQueryRequest;
import com.muyuan.goods.domains.model.entity.Goods;
import com.muyuan.goods.domains.service.GoodsService;
import com.muyuan.goods.face.dto.GoodsQueryCommand;
import com.muyuan.goods.face.dto.mapper.GoodsMapper;
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
        , interfaceClass = GoodsInterface.class
)
@AllArgsConstructor
public class GoodsInterfaceImpl implements GoodsInterface {

    private GoodsService goodsService;

    private GoodsMapper GOODS_MAPPER;

    @Override
    public Result<Page<GoodsDTO>> page(GoodsQueryRequest request) {
        GoodsQueryCommand command = GOODS_MAPPER.toCommand(request);
        Page<Goods> page =  goodsService.page(command);

        return ResultUtil.success(Page.copy(page, GOODS_MAPPER.toDTO(page.getRows())));
    }
}
