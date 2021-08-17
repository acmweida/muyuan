package com.muyuan.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InternalStrUtil {

    /**
     * 驼峰转下划线
     * @param humpString
     * created by hbd 20160722
     * @return
     */
    public static String humpToUnderline(String humpString) {
        if(null == humpString || humpString.isEmpty()) return "";
        String regexStr = "[A-Z]";
        Matcher matcher = Pattern.compile(regexStr).matcher(humpString);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String g = matcher.group();
            matcher.appendReplacement(sb, "_" + g);
        }
        matcher.appendTail(sb);
        if (sb.charAt(0) == '_') {
            sb.delete(0, 1);
        }
        return sb.toString().toLowerCase();
    }
}
