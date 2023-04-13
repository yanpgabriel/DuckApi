package com.yanpgabriel.duck.modules.auth;

import com.yanpgabriel.duck.responses.BaseResponse;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    AuthService service;

    @POST
    @Path("/login")
    public Response login(@Valid LoginDTO loginDTO) {
        return BaseResponse.instaceSuccess().entity(service.login(loginDTO)).toResponse();
    }

    @GET
    @Authenticated
    @Path("/check")
    public Response check() {
        return BaseResponse.instaceSuccess().toResponse();
    }

    @GET
    @PermitAll
    @Path("/info")
    public Response info() {
        return BaseResponse.instaceSuccess().entity(jwt.getGroups()).toResponse();
    }

    @GET
    @Authenticated
    @Path("/retoken/{uuid}")
    public Response retoken(@PathParam("uuid") String uuid) {
        return BaseResponse.instaceSuccess().entity(service.retoken(uuid)).toResponse();
    }
    
}
