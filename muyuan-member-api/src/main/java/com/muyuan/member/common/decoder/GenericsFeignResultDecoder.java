package com.muyuan.member.common.decoder;

import com.muyuan.common.util.JSONUtil;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.val;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class GenericsFeignResultDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        type.getTypeName();
        if (type instanceof ParameterizedTypeImpl) {
            System.out.println(type.getTypeName());
            System.out.println(((ParameterizedTypeImpl) type).getRawType());
            System.out.println(((ParameterizedTypeImpl) type).getActualTypeArguments());

            System.out.println(response.body().asInputStream().available());
            InputStream inputStream = response.body().asInputStream();
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            String jsonData = new String(data);
            HashMap hashMap = JSONUtil.parseObject(jsonData, HashMap.class);
            System.out.println(hashMap.toString());
            try {
                 HashMap data1 = (HashMap) hashMap.get("data");
                 Class[] classes = new Class[((ParameterizedTypeImpl) type).getActualTypeArguments().length];
                 Object[] values =new Object[classes.length];
                 int i=0;
                for (Type type1 :  ((ParameterizedTypeImpl) type).getActualTypeArguments()) {
                    classes[i]= ((ParameterizedTypeImpl) type).getRawType().getClass();
                    values[i++] = null;
                }
                System.out.println(((ParameterizedTypeImpl) type).getRawType().getConstructor(classes).newInstance(values));
                System.out.println("---------------------------");
                System.out.println(((ParameterizedTypeImpl) type).getRawType().getConstructor(((ParameterizedTypeImpl) type).getActualTypeArguments().getClass()).newInstance());
                System.out.println(data1);
                System.out.println(((ParameterizedTypeImpl) type).getRawType().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
