package com.yanpgabriel.duck.modules.financas.conta;

import com.yanpgabriel.duck.responses.BaseResponse;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/financas/conta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContaResource {

    @Inject
    ContaService service;

    @GET
    @Authenticated
    public Response obterTodos() {
        return BaseResponse.instaceSuccess().entity(service.obterTodos()).toResponse();
    }

    @GET
    @Path("/saldos")
    @Authenticated
    public Response obterSaldos() {
        return BaseResponse.instaceSuccess().entity(service.obterSaldos()).toResponse();
    }
    @GET
    @Path("/saldo-total")
    @Authenticated
    public Response obterSaldoTotal() {
        return BaseResponse.instaceSuccess().entity(service.obterSaldoTotal()).toResponse();
    }

}
