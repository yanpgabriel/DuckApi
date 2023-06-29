package com.yanpgabriel.duck.modules.user;

import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.responses.TypeResponse;
import com.yanpgabriel.duck.util.DuckUtil;
import com.yanpgabriel.duck.util.config.DuckRoles;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource  {

    @Inject
    UserService service;

    @GET
    @RolesAllowed({DuckRoles.DUCK_ADM, DuckRoles.DUCK_USER_LIST})
    public Response list(@QueryParam("sort") String sortQuery,
                         @QueryParam("page") @DefaultValue("0") int pageIndex,
                         @QueryParam("size") @DefaultValue("10") int pageSize) {
        Page page = Page.of(pageIndex, pageSize);
        Sort sort = DuckUtil.makeSort(sortQuery);
        return BaseResponse.instaceSuccess().entity(service.list(page, sort)).toResponse();
    }

    @GET
    @Path("/{idUsuario}")
    @RolesAllowed({DuckRoles.DUCK_ADM, DuckRoles.DUCK_USER_EDIT})
    public Response get(@PathParam("idUsuario") Long idUser) {
        return BaseResponse.instaceSuccess().entity(service.getById(idUser)).toResponse();
    }

    @POST
    @RolesAllowed({DuckRoles.DUCK_ADM, DuckRoles.DUCK_USER_EDIT})
    public Response create(UserDTO userDTO) {
        BaseResponse baseResponse = BaseResponse.instace();
        try {
           return service.create(userDTO);
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
       }
    }

    @PUT
    @RolesAllowed({DuckRoles.DUCK_ADM, DuckRoles.DUCK_USER_EDIT})
    public Response update(UserDTO userDTO) {
        BaseResponse baseResponse = BaseResponse.instace();
        try {
           return service.update(userDTO);
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
       }
    }

    @DELETE
    @Path("/{idUsuario}")
    @RolesAllowed({DuckRoles.DUCK_ADM, DuckRoles.DUCK_USER_EDIT})
    public Response delete(@PathParam("idUsuario") Long idUsuario) {
        BaseResponse baseResponse = BaseResponse.instace();
        try {
            return service.delete(idUsuario);
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

    @GET
    @Path("/is-admin")
    @RolesAllowed(DuckRoles.DUCK_ADM)
    public Response isAdmin() {
        return BaseResponse.instaceSuccess().toResponse();
    }

}
