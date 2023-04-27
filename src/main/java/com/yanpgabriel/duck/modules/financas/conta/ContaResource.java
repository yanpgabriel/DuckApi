package com.yanpgabriel.duck.modules.financas.conta;

import com.yanpgabriel.duck.responses.BaseResponse;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
