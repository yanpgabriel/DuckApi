package com.yanpgabriel.duck.modules.mail;

import com.yanpgabriel.duck.responses.BaseResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/mail")
public class EmailResource {

    @Inject
    EmailService service;
    @GET
    public Response sendEmail() {
        try {
            service.send();
            return BaseResponse.instaceSuccess().entity("Email enviado!").toResponse();
        } catch (Exception e) {
            return BaseResponse.instaceError().status(405).entity(e.getMessage()).toResponse();
        }
    }

}
