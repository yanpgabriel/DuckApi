package dev.yanpgabriel.duck.modules.user;

import dev.yanpgabriel.duck.responses.BaseResponse;
import dev.yanpgabriel.duck.responses.TypeResponse;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource  {
    
    @Inject
    UserService service;
    
    @GET
    @RolesAllowed({"DUCK_ADM", "USER_LIST"})
    public Response list() {
        return new BaseResponse().entity(UserEntity.listAll()).toResponse();
    }
    
    @GET
    @Path("/{keycloackId}")
    public Response hasUser(@PathParam("keycloackId") String keycloackId) {
        return new BaseResponse().entity(service.findByKeycloackId(keycloackId)).toResponse();
    }
    
    @POST
    public Response create(UserEntity userEntity) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.create(userEntity)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extras(e.getMessage()).toResponse();
       }
    }

    @GET
    @Path("/is-admin")
    @RolesAllowed("DUCK_ADM")
    public Response isAdmin() {
        return new BaseResponse().entity("Ok").toResponse();
    }
    
}
