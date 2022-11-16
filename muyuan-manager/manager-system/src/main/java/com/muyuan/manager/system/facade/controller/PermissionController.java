package com.muyuan.manager.system.facade.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.system.dto.PermissionParams;
import com.muyuan.manager.system.dto.PermissionQueryParams;
import com.muyuan.manager.system.dto.converter.PermissionConverter;
import com.muyuan.manager.system.dto.vo.PermissionVO;
import com.muyuan.manager.system.service.PermissionService;
import com.muyuan.user.api.dto.PermissionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"权限数据接口"})
@AllArgsConstructor
public class PermissionController {

    private PermissionService permissionService;

    private PermissionConverter converter;

    @GetMapping("/permission/list")
    @ApiOperation(value = "字典数值列表查询")
    @RequirePermissions(value = "system:perms:query")
    public Result<Page<PermissionVO>> list(@ModelAttribute PermissionQueryParams params) {

        Page<PermissionDTO> page = permissionService.list(params);

        return ResultUtil.success(Page.copy(page, converter.toVO(page.getRows())));

    }

//    @GetMapping("/dictData/detail/{id}")
//    @ApiOperation(value = "字典数据详情数值查询")
//    @ApiImplicitParams(
//            {@ApiImplicitParam(name = "id", value = "字典数据主键", dataTypeClass = Long.class, paramType = "path", required = true)}
//    )
//    public Result<DictDataVO> detail(@PathVariable Long id) {
//        if (ObjectUtils.isEmpty(id)) {
//            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
//        }
//
//        Optional<DictDataDTO> dictData = dictDataService.getById(id);
//
//        return dictData.map(dictDataDTO -> ResultUtil.success(converter.toVO(dictDataDTO))).orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
//    }
//
//
//    @PutMapping("/dictData")
//    @ApiOperation(value = "字典数据更新")
//    @RequirePermissions(value = "system:dict:edit")
//    public Result update(@RequestBody @Validated(DictDataParams.Update.class) DictDataParams dictDataParams) {
//        if (ObjectUtils.isEmpty(dictDataParams.getId())) {
//            return ResultUtil.fail("id is null");
//        }
//
//        return dictDataService.update(dictDataParams);
//    }
//
//    @DeleteMapping("/dictData/{ids}")
//    @ApiOperation(value = "字典数据删除")
//    @RequirePermissions(value = "system:dict:remove")
//    @ApiImplicitParams(
//            {@ApiImplicitParam(name = "ids", value = "字典数据主键", dataTypeClass = String.class, paramType = "path", required = true)}
//    )
//    public Result delete(@PathVariable String... ids) {
//        if (ObjectUtils.isNotEmpty(ids)) {
//            for (String id : ids) {
//                if (!StringUtils.isNumeric(id)) {
//                    return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR);
//                }
//            }
//        }
//
//        return dictDataService.deleteById(Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList()).toArray(new Long[0]));
//    }


    @PostMapping("/permission")
    @ApiOperation(value = "权限新增")
    @ApiOperationSupport(ignoreParameters = "id")
    @RequirePermissions(value = "system:perms:add")
    public Result add(@RequestBody @Validated(PermissionParams.Add.class) PermissionParams params) {
        return permissionService.add(params);
    }


}
