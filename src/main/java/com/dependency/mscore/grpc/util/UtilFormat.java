package com.dependency.mscore.grpc.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.text.SimpleDateFormat;

public class UtilFormat {

    public static <T> T objectMapping(Object object, Class<T> targetType) throws Exception {
        try {
            ObjectMapper objMapper = ((JsonMapper.Builder) ((JsonMapper.Builder) ((JsonMapper.Builder) JsonMapper.builder().enable(new MapperFeature[]{MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES})).disable(new SerializationFeature[]{SerializationFeature.FAIL_ON_EMPTY_BEANS})).defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))).build();
            return objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).convertValue(object, targetType);
        } catch (Exception var3) {
            Exception e = var3;
            throw new Exception("Error in objectMapping", e);
        }
    }
}
