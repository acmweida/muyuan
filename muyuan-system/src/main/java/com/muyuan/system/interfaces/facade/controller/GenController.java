package com.muyuan.system.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.core.util.Convert;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.system.domains.model.GenTable;
import com.muyuan.system.domains.model.GenTableColumn;
import com.muyuan.system.domains.service.GenTableColumnService;
import com.muyuan.system.domains.service.GenTableDomainService;
import com.muyuan.system.domains.dto.GenTableDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 * 
 * @author ruoyi
 */
@RequestMapping("/gen")
@RestController
@AllArgsConstructor
public class GenController {

    private GenTableDomainService genTableDomainService;

    private GenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @RequirePermissions("tool:gen:list")
    @GetMapping("/list")
    public Result genList(GenTableDTO genTableDTO)
    {
        Page<GenTable> res = genTableDomainService.selectGenTableList(genTableDTO);
        return ResultUtil.success(res);
    }

    /**
     * 修改代码生成业务
     */
    @RequirePermissions("tool:gen:query")
    @GetMapping(value = "/{tableId}")
    public Result getInfo(@PathVariable Long tableId)
    {
        GenTable table = genTableDomainService.selectGenTableById(tableId);
        List<GenTable> tables = genTableDomainService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return ResultUtil.success(map);
    }

    /**
     * 查询数据库列表
     */
    @RequirePermissions("tool:gen:list")
    @GetMapping("/db/list")
    public Result dataList(GenTableDTO genTableDTO)
    {
        Page<GenTable> page = genTableDomainService.selectDbTableList(genTableDTO);
        return ResultUtil.success(page);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableId}")
    public Result columnList(Long tableId)
    {
//        TableDataInfo dataInfo = new TableDataInfo();
//        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
//        dataInfo.setRows(list);
//        dataInfo.setTotal(list.size());
//        return dataInfo;
        return ResultUtil.success();
    }

    /**
     * 导入表结构（保存）
     */
    @RequirePermissions("tool:gen:import")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public Result importTableSave(String tables)
    {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableDomainService.selectDbTableListByNames(tableNames);
        genTableDomainService.importGenTable(tableList);
        return ResultUtil.success();
    }

    /**
     * 修改保存代码生成业务
     */
    @RequirePermissions("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result editSave(@Validated @RequestBody GenTableDTO genTable)
    {
        genTableDomainService.validateEdit(genTable);
        genTableDomainService.updateGenTable(genTable);
        return ResultUtil.success();
    }

    /**
     * 删除代码生成
     */
    @RequirePermissions("tool:gen:remove")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public Result remove(@PathVariable Long[] tableIds)
    {
        genTableDomainService.deleteGenTableByIds(tableIds);
        return ResultUtil.success();
    }

    /**
     * 预览代码
     */
    @RequirePermissions("tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    public Result preview(@PathVariable("tableId") Long tableId) throws IOException
    {
        Map<String, String> dataMap = genTableDomainService.previewCode(tableId);
        return ResultUtil.success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @RequirePermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
        byte[] data = genTableDomainService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @RequirePermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public Result genCode(@PathVariable("tableName") String tableName)
    {
        genTableDomainService.generatorCode(tableName);
        return ResultUtil.success();
    }

    /**
     * 同步数据库
     */
    @RequirePermissions("tool:gen:edit")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/synchDb/{tableName}")
    public Result synchDb(@PathVariable("tableName") String tableName)
    {
        genTableDomainService.synchDb(tableName);
        return ResultUtil.success();
    }

    /**
     * 批量生成代码
     */
    @RequirePermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException
    {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableDomainService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"template.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
