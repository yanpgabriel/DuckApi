package dev.yanpgabriel.duck.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.ValidationException;

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
