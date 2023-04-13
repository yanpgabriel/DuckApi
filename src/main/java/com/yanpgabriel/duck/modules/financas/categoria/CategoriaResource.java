package com.yanpgabriel.duck.modules.financas.categoria;

import com.yanpgabriel.duck.responses.BaseResponse;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/financas/categoria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @GET
    @Authenticated
    public Response obterTodas() {
        return BaseResponse.instaceSuccess().entity(service.obterTodas()).toResponse();
    }

    @GET
    @Path("/conta")
    @Authenticated
    public Response obterTodasDeConta() {
        return BaseResponse.instaceSuccess().entity(service.obterTodasDeConta()).toResponse();
    }

    @GET
    @Path("/receita")
    @Authenticated
    public Response obterTodasDeReceita() {
        return BaseResponse.instaceSuccess().entity(service.obterTodasDeReceita()).toResponse();
    }

    @GET
    @Path("/despesa")
    @Authenticated
    public Response obterTodasDeDespesa() {
        return BaseResponse.instaceSuccess().entity(service.obterTodasDeDespesa()).toResponse();
    }

}
