package com.muyuan.goods.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.goods.api.BrandInterface;
import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.goods.api.dto.BrandQueryRequest;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.service.BrandService;
import com.muyuan.goods.face.dto.BrandQueryCommand;
import com.muyuan.goods.face.dto.mapper.BrandMapper;
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
        , interfaceClass = BrandInterface.class
)
@AllArgsConstructor
public class BrandInterfaceImpl implements BrandInterface {

    private BrandService brandService;

    private BrandMapper BRAND_MAPPER;

    @Override
    public Result<Page<BrandDTO>> list(BrandQueryRequest request) {
        BrandQueryCommand command = BRAND_MAPPER.toCommand(request);
        Page<Brand> page =  brandService.list(command);

        return ResultUtil.success(Page.copy(page, BRAND_MAPPER.toDTO(page.getRows())));
    }
}
