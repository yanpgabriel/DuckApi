package com.yanpgabriel.duck.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

public class DuckJson {
    
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = applyDefaultConfig(new ObjectMapper());
    }  
    
    private static ObjectMapper applyDefaultConfig(ObjectMapper objectMapper) {
        return objectMapper
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
     
    public static ObjectMapper objectMapperIgnoreNullFields() {
        return applyDefaultConfig(new ObjectMapper())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    
    public static String toJson(Object obj) {
        return toJson(objectMapper, obj);
    }
    
    public static String toJson(ObjectMapper objectMapper, Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao serializar o json.");
        }
    }

    public static Map<String, Object> toMap(Object obj) {
        return toMap(objectMapper, obj);
    }

    public static Map<String, Object> toMap(ObjectMapper objectMapper, Object obj) {
        return objectMapper.convertValue(obj, Map.class);
    }
    
}
