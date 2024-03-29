package com.yanpgabriel.duck.modules.kanban.demanda;

import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.responses.TypeResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/demanda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DemandaResource {
    
    @Inject
    DemandaService service;

    @DELETE
    @Path("{idDemanda}")
    public Response delete(@PathParam(value = "idDemanda") Long idDemanda) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            service.delete(idDemanda);
            return baseResponse.status(204).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

    @POST
    public Response create(@Valid DemandaDTO demanda) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.create(demanda)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
       }
    }

    @PUT
    public Response update(DemandaDTO demanda) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.update(demanda)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
       }
    }

    @GET
    public Response list() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            return baseResponse.entity(service.list()).status(200).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

    @GET
    @Path("{idDemanda}")
    public Response get(@PathParam(value = "idDemanda") Long idDemanda) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            return baseResponse.entity(service.get(idDemanda)).status(200).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

}
