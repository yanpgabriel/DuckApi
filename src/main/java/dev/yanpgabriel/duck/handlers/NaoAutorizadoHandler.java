package dev.yanpgabriel.duck.handlers;

import dev.yanpgabriel.duck.exceptions.NaoAutorizadoException;
import dev.yanpgabriel.duck.responses.BaseResponse;
import dev.yanpgabriel.duck.responses.TypeResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NaoAutorizadoHandler implements ExceptionMapper<NaoAutorizadoException> {
    public Response toResponse(NaoAutorizadoException e) {
        return  BaseResponse.instace()
                .type(TypeResponse.ERROR)
                .status(Response.Status.UNAUTHORIZED.getStatusCode())
                .extras(e.getMessage())
                .toResponse();
    }
}
