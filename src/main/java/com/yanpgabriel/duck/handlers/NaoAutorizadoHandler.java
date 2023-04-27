package com.yanpgabriel.duck.handlers;

import com.yanpgabriel.duck.exceptions.NaoAutorizadoException;
import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.responses.TypeResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NaoAutorizadoHandler implements ExceptionMapper<NaoAutorizadoException> {
    public Response toResponse(NaoAutorizadoException e) {
        return  BaseResponse.instace()
                .type(TypeResponse.ERROR)
                .status(Response.Status.UNAUTHORIZED.getStatusCode())
                .extra(e.getMessage())
                .toResponse();
    }
}
