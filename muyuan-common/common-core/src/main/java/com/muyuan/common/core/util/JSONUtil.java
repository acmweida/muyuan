package com.muyuan.common.core.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.muyuan.common.core.constant.GlobalConst;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许出现特殊字符和转义符
        objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        // 允许出现单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.setDateFormat(new SimpleDateFormat(GlobalConst.DEFAULT_DATE_FORMAT));
        // 字段保留，将null值转为""
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider)
                    throws IOException {
                jsonGenerator.writeString("");
            }
        });

        // 定义数组和集合元素为0时,返回“[]”
        objectMapper.setSerializerFactory(
                objectMapper.getSerializerFactory()
                        .withSerializerModifier(new BeanSerializerModifier() {

                            @Override
                            public JsonSerializer<?> modifyArraySerializer(SerializationConfig config, ArrayType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
                                JsonSerializer<?> jsonSerializer = super.modifyArraySerializer(config, valueType, beanDesc, serializer);
                                return  new EmptyGenericArrayJsonSerializer(jsonSerializer);
                            }

                            @Override
                            public JsonSerializer<?> modifyCollectionSerializer(SerializationConfig config, CollectionType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
                                return new EmptyGenericArrayJsonSerializer(super.modifyCollectionSerializer(config, valueType, beanDesc, serializer));
                            }

                        })
        );
    }

    static class EmptyGenericArrayJsonSerializer extends JsonSerializer<Object> {

        private JsonSerializer defaultSerializer;

        public EmptyGenericArrayJsonSerializer(JsonSerializer defaultSerializer) {
            this.defaultSerializer = defaultSerializer;
        }

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (isArrayType(value)) {
                if ( (value.getClass().isArray() && ArrayUtils.getLength(value) == 0)
                        || CollectionUtils.isEmpty((Collection<?>) value)) {
                    gen.writeStartArray();
                    gen.writeEndArray();
                }  else {
                    defaultSerializer.serialize(value,gen,serializers);
                }
            }
        }

        public boolean isArrayType(Object value) {
            Class<?> rawClass = value.getClass();
            return rawClass.isArray() || Collection.class.isAssignableFrom(rawClass);
        }
    }


    public static String toJsonString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("JSON Parse ERROR", e);
        }
        return null;
    }

    public static JsonNode parse(String jsonstr) {
        try {
            return objectMapper.readTree(jsonstr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            log.error("Json parser error", e);
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T parseObject(String jsonStr, CollectionType clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            log.error("Json parser error", e);
            e.printStackTrace();
        }
        return null;
    }

    public static Collection parseObjectList(String jsonStr, Class<? extends Collection> collectionClazz, Class<?> elementClass) {
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(collectionClazz, elementClass);
        return parseObject(jsonStr, collectionType);
    }

    public static <T> T coverValue(Map<String, Object> data, Class<T> clazz) {
        return objectMapper.convertValue(data, clazz);
    }

    public static JsonNode readTree(String content) {
        try {
            return objectMapper.readTree(content);
        } catch (JsonProcessingException e) {
            log.error("Json parser error", e);
            e.printStackTrace();
        }
        return null;

    }
}
