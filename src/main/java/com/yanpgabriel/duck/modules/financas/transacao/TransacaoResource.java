package com.yanpgabriel.duck.modules.financas.transacao;

import com.yanpgabriel.duck.responses.BaseResponse;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
