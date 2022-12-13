package com.muyuan.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.repo.BrandRepo;
import com.muyuan.goods.domains.service.BrandService;
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
}
