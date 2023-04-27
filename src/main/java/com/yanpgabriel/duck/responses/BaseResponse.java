package com.yanpgabriel.duck.responses;


import com.yanpgabriel.duck.util.DuckJson;
import jakarta.ws.rs.core.Response;

import java.util.*;

public class BaseResponse {

    private Object entity;
    private Integer status;
    private TypeResponse type ;
    private List<String> extras = new ArrayList<>();
    private Map<String, Object> headers = new HashMap<>();

    public BaseResponse() {
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
    
    public static BaseResponse instace() {
        return new BaseResponse(TypeResponse.UNKNOWN, 500, new ArrayList<>());
    }
    
    public static BaseResponse instaceSuccess() {
        return new BaseResponse(TypeResponse.SUCCESS, 204, null,  new ArrayList<>());
    }
    
    public static BaseResponse instaceError() {
        return new BaseResponse(TypeResponse.ERROR, 400, null,  new ArrayList<>());
    }
    
    public static BaseResponse instaceServerError() {
        return new BaseResponse(TypeResponse.SERVER_ERRO, 500, null,  new ArrayList<>());
    }

    public static BaseResponse instaceFile() {
        return new BaseResponse(TypeResponse.FILE, 200, null,  new ArrayList<>());
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

    public void addExtra(String extras) {
        this.extras.add(extras);
    }
    public BaseResponse extra(String extra) {
        this.extras.add(extra);
        return this;
    }
    public BaseResponse extras(List<String> extras) {
        this.extras = extras;
        return this;
    }

    public void addHeader(String headerKey, Object headerValue) {
        this.headers.put(headerKey, headerValue);
    }
    public BaseResponse header(String headerKey, Object headerValue) {
        this.headers.put(headerKey, headerValue);
        return this;
    }
    public BaseResponse headers(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public Response toResponse() {
        if (this.type == TypeResponse.SUCCESS && Objects.nonNull(this.entity)) {
            this.status = 200;
        }
        Response.ResponseBuilder responseBuilder = Response.status(this.status);
        responseBuilder = this.type != TypeResponse.FILE ? responseBuilder.entity(this.toString()) : responseBuilder.entity(this.entity);
        if (!this.headers.isEmpty()) {
            for (var entry : this.headers.entrySet()) {
                responseBuilder = responseBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        return responseBuilder.build();
    }

    public String toString() {
        try {
            return DuckJson.toJson(this);
        } catch (RuntimeException e) {
            return "Erro ao serializar resposta.";
        }
    }
}
