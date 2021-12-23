package com.muyuan.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.muyuan.common.constant.CommonConst;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @ClassName JSONUtil
 * Description JSON 工具类
 * @Author 2456910384
 * @Date 2021/12/6 16:43
 * @Version 1.0
 */
@Data
@Slf4j
public class JSONUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许出现特殊字符和转义符
        objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        // 允许出现单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.setDateFormat(new SimpleDateFormat(CommonConst.DEFAULT_DATE_FORMAT));
        // 字段保留，将null值转为""
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()
        {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider)
                    throws IOException
            {
                jsonGenerator.writeString("");
            }
        });
    }

    public static String toJsonString(Object o)  {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("JSON Parse ERROR",e);
        }
        return null;
    }

    public static  <T> T parseObject(String jsonStr, Class<T> clazz) {
        try {
          return   objectMapper.readValue(jsonStr,clazz);
        } catch (JsonProcessingException e) {
            log.error("Json parser error",e);
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T coverValue(Map<String,Object> data,Class<T> clazz) {
       return objectMapper.convertValue(data,clazz);
    }
}
