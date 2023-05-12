package com.yanpgabriel.duck.modules.financas.categoria;

import com.yanpgabriel.duck.responses.BaseResponse;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    @GET
    @Path("/transacao")
    @Authenticated
    public Response obterTodasDeTransacao() {
        return BaseResponse.instaceSuccess().entity(service.obterTodasDeTransacao()).toResponse();
    }

}
