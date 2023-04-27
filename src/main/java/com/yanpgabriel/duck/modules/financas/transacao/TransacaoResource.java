package com.yanpgabriel.duck.modules.financas.transacao;

import com.yanpgabriel.duck.responses.BaseResponse;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/financas/transacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransacaoResource {

    @Inject
    TransacaoService service;

    @GET
    @Authenticated
    public Response obterTodas() {
        return BaseResponse.instaceSuccess().entity(service.obterTodas()).toResponse();
    }

    @GET
    @Path("/receitas")
    @Authenticated
    public Response obterTodasReceitas() {
        return BaseResponse.instaceSuccess().entity(service.obterTodasReceitas()).toResponse();
    }

    @GET
    @Path("/despesas")
    @Authenticated
    public Response obterTodasDespesas() {
        return BaseResponse.instaceSuccess().entity(service.obterTodasDespesas()).toResponse();
    }

}
