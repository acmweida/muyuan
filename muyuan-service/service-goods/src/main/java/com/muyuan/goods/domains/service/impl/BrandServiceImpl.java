package com.muyuan.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.thread.CommonThreadPool;
import com.muyuan.common.core.util.CacheServiceUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.goods.domains.enums.BrandAuthStatus;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.model.entity.Category;
import com.muyuan.goods.domains.repo.BrandRepo;
import com.muyuan.goods.domains.repo.CategoryRepo;
import com.muyuan.goods.domains.service.BrandService;
import com.muyuan.goods.face.dto.BrandCommand;
import com.muyuan.goods.face.dto.BrandQueryCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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

    private CategoryRepo categoryRepo;

    private RedisCacheService cacheService;

    private static String BRAND_KEY_PREFIX = "BRAND_OPTIONS:";

    @Override
    public Page<Brand> list(BrandQueryCommand commend) {
        return brandRepo.select(commend);
    }

    @Override
    public List<Brand> listByCategoryCode(Long... categoryCodes) {
        if (ObjectUtils.isEmpty(categoryCodes)) {
            return GlobalConst.EMPTY_LIST;
        }

        List<Brand> brands = new ArrayList<>();
        for (Long categoryCode : categoryCodes) {
            String key = BRAND_KEY_PREFIX + categoryCode;
            List<Brand> tempList = CacheServiceUtil.getAndUpdateList(cacheService, key, () -> {
                return brandRepo.selectByCategoryCode(categoryCodes);
            }, Brand.class);
            brands.addAll(tempList);
        }

        return brands.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(Comparator.comparing(Brand::getId))), ArrayList::new)
        );
    }

    @Override
    public Optional<Brand> get(Long id, BrandAuthStatus... authStatus) {
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
        brand.setStatus(command.getStatus());

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return false;
        }
        List<Long> removeIds = new ArrayList<>(Arrays.asList(ids));

        List<Brand> olds = brandRepo.deleteBy(removeIds.toArray(new Long[0]));
        brandRepo.deleteRef(olds.stream().map(Brand::getId).collect(Collectors.toList()));

        Runnable task = () -> {
            for (Brand old : olds) {
                List<Category> categories = categoryRepo.selectByBrandId(old.getId());
                if (ObjectUtils.isNotEmpty(categories)) {
                    for (Category category : categories) {
                        cacheService.delayDoubleDel(BRAND_KEY_PREFIX + category.getCode());
                    }
                }
            }
        };

        CommonThreadPool.exec(task);

        return !olds.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean linkCategory(Brand brand, Long... categoryCodes) {
        if (ObjectUtils.isEmpty(brand)) {
            return false;
        }

        if (ObjectUtils.isEmpty(categoryCodes)) {
            brandRepo.deleteRef(Collections.singletonList(brand.getId()));
        } else {
            List<Category> categories = categoryRepo.selectByBrandId(brand.getId());
            List<Long> codes = categories.stream().map(Category::getCode).collect(Collectors.toList());
            List<Long> common = ListUtils.retainAll(Arrays.asList(categoryCodes), codes);

            // 删除
            List<Long> temp = new ArrayList<>();
            for (Long code : codes) {
                if (!common.contains(code)) {
                    temp.add(code);
                }
            }
            brandRepo.deleteRef(brand.getId(),temp.toArray(new Long[0]));

            // 新增
            temp.clear();
            for (Long code : categoryCodes) {
                if (!common.contains(code)) {
                    temp.add(code);
                }
            }
            brandRepo.addRef(brand.getId(),temp.toArray(new Long[0]));

        }

        Runnable task = () -> {
            for (Long categoryCode : categoryCodes) {
                cacheService.delayDoubleDel(BRAND_KEY_PREFIX + categoryCode);
            }
        };

        CommonThreadPool.exec(task);

        return true;
    }
}
