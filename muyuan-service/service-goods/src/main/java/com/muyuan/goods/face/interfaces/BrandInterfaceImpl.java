package com.muyuan.goods.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.goods.api.BrandInterface;
import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.goods.api.dto.BrandQueryRequest;
import com.muyuan.goods.api.dto.BrandRequest;
import com.muyuan.goods.domains.enums.BrandAuthStatus;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.application.BrandService;
import com.muyuan.goods.face.dto.BrandQueryCommand;
import com.muyuan.goods.face.dto.transfor.BrandTransfer;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.List;
import java.util.Optional;


/**
 * @ClassName GoodsInterfaceImpl
 * Description 商品服务接口实现
 * @Author 2456910384
 * @Date 2022/8/25 15:42
 * @Version 1.0
 */
@DubboService(group = ServiceTypeConst.GOODS, version = "1.0"
        , interfaceClass = BrandInterface.class,
        methods = {
                @Method(name = "add", retries = 0),
                @Method(name = "linkCategory", retries = 0)
        }
)
@AllArgsConstructor
public class BrandInterfaceImpl implements BrandInterface {

    private BrandService brandService;

    private BrandTransfer BRAND_MAPPER;

    @Override
    public Result<Page<BrandDTO>> list(BrandQueryRequest request) {
        BrandQueryCommand command = BRAND_MAPPER.toCommand(request);
        Page<Brand> page = brandService.list(command);

        return ResultUtil.success(Page.copy(page, BRAND_MAPPER.toDTO(page.getRows())));
    }

    @Override
    public Result<List<BrandDTO>> listByCCategoryCode(Long... categoryCodes) {
        List<Brand> list = brandService.listByCategoryCode(categoryCodes);
        return ResultUtil.success(BRAND_MAPPER.toDTO(list));
    }

    @Override
    public Result linkCategory(Long brandId, Long... categoryCodes) {
        Optional<Brand> handler = brandService.get(brandId, BrandAuthStatus.PASS);
        if (!handler.isPresent()) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        return handler
                .map(brand -> brandService.linkCategory(brand, categoryCodes) ? brand : null)
                .map(brand -> ResultUtil.success())
                .orElse(ResultUtil.error(ResponseCode.FAIL.getCode(), "关联类目失败"));
    }

    @Override
    public Result<BrandDTO> get(Long id) {
        Optional<Brand> handler = brandService.get(id);

        return handler.map(BRAND_MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result add(BrandRequest request) {
        if (brandService.exists(new Brand.Identify(request.getName()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = brandService.add(BRAND_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result update(BrandRequest request) {
        if (brandService.exists(new Brand.Identify(request.getId(), request.getName()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = brandService.update(BRAND_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result audit(Long id, Integer auditStatus) {
        Optional<Brand> handler = brandService.get(id);
        if (!handler.isPresent()) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        return handler
                .map(brand -> brandService.audit(brand, auditStatus) ? brand : null)
                .map(brand -> ResultUtil.success())
                .orElse(ResultUtil.error(ResponseCode.FAIL.getCode(), "认证状态失败"));
    }

    @Override
    public Result delete(Long... ids) {
        if (brandService.delete(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
