package com.muyuan.shop.interfaces.facade;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@Api(tags = {"店铺基本信息接口"})
public interface ShopController {

    
}
