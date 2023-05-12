package com.yanpgabriel.duck.handlers;

import com.yanpgabriel.duck.responses.BaseResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GenericoHandler implements ExceptionMapper<Exception> {

    Logger logger = LoggerFactory.getLogger(GenericoHandler.class);

    public Response toResponse(Exception e) {
        logger.error(e.getMessage());
        return BaseResponse.instaceServerError().extra(e.getMessage()).toResponse();
    }
}
