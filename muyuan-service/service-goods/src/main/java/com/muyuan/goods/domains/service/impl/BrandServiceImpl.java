package com.muyuan.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.repo.BrandRepo;
import com.muyuan.goods.domains.service.BrandService;
import com.muyuan.goods.face.dto.BrandCommand;
import com.muyuan.goods.face.dto.BrandQueryCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName GoodsServiceImpl
 * Description 商品服务
 * @Author 2456910384
 * @Date 2022/8/26 14:09
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandRepo brandRepo;

    @Override
    public Page<Brand> list(BrandQueryCommand commend) {
        return brandRepo.select(commend);
    }

    @Override
    public Optional<Brand> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    return brandRepo.select(id);
                });
    }

    @Override
    public String checkUnique(Brand.Identify identify) {
        Long id = null == identify.getId() ? 0 : identify.getId();
        Brand brand = brandRepo.select(identify);
        if (null != brand && !id.equals(brand.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public boolean add(BrandCommand command) {
        Brand brand = new Brand();

        brand.setName( command.getName() );
        brand.setLogo( command.getLogo() );
        brand.setOrderNum( command.getOrderNum() );
        brand.setEnglishName( command.getEnglishName() );
        brand.setRemark( command.getRemark() );

        brand.init(command.getOpt());

        return brandRepo.add(brand);
    }

    @Override
    public boolean update(BrandCommand command) {
        Brand brand = new Brand();

        brand.setId(command.getId());
        brand.setName( command.getName() );
        brand.setLogo( command.getLogo() );
        brand.setOrderNum( command.getOrderNum() );
        brand.setEnglishName( command.getEnglishName() );
        brand.setRemark( command.getRemark() );

        brand.update(command.getOpt());

        return brandRepo.update(brand);
    }

    @Override
    public boolean audit(Brand brand, Integer auditStatus) {
        if (brand.audit(auditStatus)) {
          return  brandRepo.update(brand);
        }
        return false;
    }
}
