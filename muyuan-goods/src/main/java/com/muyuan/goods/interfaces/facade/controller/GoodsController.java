package com.muyuan.goods.interfaces.facade.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@Api(tags = {"商品接口"})
@RestController
public interface GoodsController {
}
