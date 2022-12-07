package dev.yanpgabriel.duck.modules.kanban.demanda;

import dev.yanpgabriel.duck.responses.BaseResponse;
import dev.yanpgabriel.duck.responses.TypeResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
            return baseResponse.status(410).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extras(e.getMessage()).toResponse();
        }
    }

    @POST
    public Response create(@Valid DemandaDTO demanda) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.create(demanda)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extras(e.getMessage()).toResponse();
       }
    }

    @PUT
    public Response update(DemandaDTO demanda) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.update(demanda)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extras(e.getMessage()).toResponse();
       }
    }

    @GET
    public Response list() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            return baseResponse.entity(service.list()).status(200).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extras(e.getMessage()).toResponse();
        }
    }

    @GET
    @Path("{idDemanda}")
    public Response get(@PathParam(value = "idDemanda") Long idDemanda) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            return baseResponse.entity(service.get(idDemanda)).status(200).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extras(e.getMessage()).toResponse();
        }
    }

}
