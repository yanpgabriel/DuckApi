package com.yanpgabriel.duck.modules.apps;

import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.responses.TypeResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/app")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AppResource {

    @Inject
    AppService service;

    @GET
    @RolesAllowed({"DUCK_ADM"})
    public Response list(@QueryParam("sort") String sortQuery,
                         @QueryParam("page") @DefaultValue("0") int pageIndex,
                         @QueryParam("size") @DefaultValue("10") int pageSize) {
        Page page = Page.of(pageIndex, pageSize);
        Sort sort = Sort.by(sortQuery);
        return BaseResponse.instaceSuccess().entity(service.list(page, sort)).toResponse();
    }

    @GET
    @Path("/{idApp}")
    @RolesAllowed({"DUCK_ADM"})
    public Response get(@PathParam("idApp") Long idApp) {
        return BaseResponse.instaceSuccess().entity(service.get(idApp)).toResponse();
    }

    @POST
    public Response create(AppEntity appEntity) {
        BaseResponse baseResponse = BaseResponse.instace();
        try {
           return baseResponse.entity(service.create(appEntity)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extras(e.getMessage()).toResponse();
       }
    }
}
