package dev.yanpgabriel.duck.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// @Provider
// public class RuntimeHandler implements ExceptionMapper<RuntimeException> {
public class RuntimeHandler {
    public Response toResponse(RuntimeException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .header("Content-Type", MediaType.TEXT_PLAIN)
                .entity(e.getMessage())
                .build();
    }
}
