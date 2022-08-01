package dev.yanpgabriel.duck.responses;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaseResponse {

    private TypeResponse type = TypeResponse.SUCCESS;
    private Integer status = 200;
    private Object entity;
    private List<String> extras = new ArrayList<>();

    public BaseResponse() {
        super();
    }
    public BaseResponse(TypeResponse type, Integer status, Object entity) {
        super();
        this.type = type;
        this.status = status;
        this.entity = entity;
    }
    public BaseResponse(TypeResponse type, Integer status, List<String> extras) {
        super();
        this.type = type;
        this.status = status;
        this.extras = extras;
    }
    public BaseResponse(TypeResponse type, Integer status, Object entity, List<String> extras) {
        super();
        this.type = type;
        this.status = status;
        this.entity = entity;
        this.extras = extras;
    }

    public void setType(TypeResponse type) {
        this.type = type;
    }
    public BaseResponse type(TypeResponse type) {
        this.type = type;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public BaseResponse status(Integer status) {
        this.status = status;
        return this;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
    public BaseResponse entity(Object entity) {
        this.entity = entity;
        return this;
    }

    public void addExtras(String extras) {
        this.extras.add(extras);
    }
    public BaseResponse extras(String extras) {
        this.extras.add(extras);
        return this;
    }

    public Response toResponse() {
        return Response.status(this.status).entity(this.toString()).build();
    }

    public String toString() {
        // return new Gson().toJson(this);
        try {
            ObjectMapper om = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao serializar o json.");
        }
        return "";
    }
}
