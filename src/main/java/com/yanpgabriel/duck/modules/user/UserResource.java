package com.yanpgabriel.duck.modules.user;

import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.responses.TypeResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource  {

    @Inject
    UserService service;

    @GET
    @RolesAllowed({"DUCK_ADM", "USER_LIST"})
    public Response list(@QueryParam("sort") String sortQuery,
                         @QueryParam("page") @DefaultValue("0") int pageIndex,
                         @QueryParam("size") @DefaultValue("10") int pageSize) {
        Page page = Page.of(pageIndex, pageSize);
        Sort sort = Sort.by(sortQuery);
        return BaseResponse.instaceSuccess().entity(service.list(page, sort)).toResponse();
    }

    @GET
    @Path("/{idUser}")
    @RolesAllowed({"DUCK_ADM", "USER_EDIT"})
    public Response get(@PathParam("idUser") Long idUser) {
        return BaseResponse.instaceSuccess().entity(service.get(idUser)).toResponse();
    }

    @GET
    @Path("/{keycloackId}")
    public Response hasUser(@PathParam("keycloackId") String keycloackId) {
        return BaseResponse.instace().entity(service.findByKeycloackId(keycloackId)).toResponse();
    }

    @POST
    public Response create(UserEntity userEntity) {
        BaseResponse baseResponse = BaseResponse.instace();
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
        return BaseResponse.instace().entity("Ok").toResponse();
    }

}
