package com.yanpgabriel.duck.modules.mail;

import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.util.config.DuckRoles;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/mail")
public class EmailResource {

    @Inject
    EmailService service;

    @GET
    @RolesAllowed(DuckRoles.DUCK_ADM)
    public Response sendEmail() {
        try {
            service.send();
            return BaseResponse.instaceSuccess().entity("Email enviado!").toResponse();
        } catch (Exception e) {
            return BaseResponse.instaceError().status(405).extra(e.getMessage()).toResponse();
        }
    }

}
