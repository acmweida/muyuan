package com.muyuan.common.util;

import com.muyuan.common.util.string.MessageFormatter;
import lombok.extern.slf4j.Slf4j;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
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

    public static String stringFormat(String format,Object ... args) {
         return MessageFormatter.format(format, args).getMessage();
    }

}
