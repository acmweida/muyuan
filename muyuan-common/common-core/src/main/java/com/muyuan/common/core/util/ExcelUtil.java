package com.muyuan.common.core.util;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * Description Excel Util
 * @Author 2456910384
 * @Date 2022/5/9 15:55
 * @Version 1.0
 */
public class ExcelUtil {

    public static void export(HttpServletResponse response, Class entityClass, String fileName, List data) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String encodeFileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + encodeFileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), entityClass).sheet("模板").doWrite(data);
    }
}
