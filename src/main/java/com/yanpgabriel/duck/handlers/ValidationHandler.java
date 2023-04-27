package com.yanpgabriel.duck.handlers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.xml.bind.ValidationException;

@Provider
public class ValidationHandler implements ExceptionMapper<ValidationException> {
    public Response toResponse(ValidationException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .header("Content-Type", MediaType.TEXT_PLAIN)
                .entity(e.getMessage())
                .build();
    }
}
