package dev.yanpgabriel.duck.responses;


import dev.yanpgabriel.duck.util.DuckJson;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseResponse {

    private Object entity;
    private Integer status;
    private TypeResponse type ;
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
    
    public static BaseResponse instace() {
        return new BaseResponse(TypeResponse.UNKNOWN, 500, new ArrayList<>());
    }
    
    public static BaseResponse instaceSuccess() {
        return new BaseResponse(TypeResponse.SUCCESS, 200, null,  new ArrayList<>());
    }
    
    public static BaseResponse instaceError() {
        return new BaseResponse(TypeResponse.ERROR, 400, null,  new ArrayList<>());
    }
    
    public static BaseResponse instaceServerError() {
        return new BaseResponse(TypeResponse.SERVER_ERRO, 500, null,  new ArrayList<>());
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
        try {
            return DuckJson.toJson(this);
        } catch (RuntimeException e) {
            return "Erro ao serializar resposta.";
        }
    }
}
