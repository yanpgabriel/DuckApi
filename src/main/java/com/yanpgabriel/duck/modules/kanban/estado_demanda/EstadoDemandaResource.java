package com.yanpgabriel.duck.modules.kanban.estado_demanda;

import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.responses.TypeResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/estado-demanda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoDemandaResource {
    
    @Inject
    EstadoDemandaService service;

    @DELETE
    @Path("{idEstado}")
    public Response delete(@PathParam(value = "idEstado") Long idEstado) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            service.delete(idEstado);
            return baseResponse.status(410).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }
    
    @POST
    public Response create(EstadoDemandaDTO estadoDemandaDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.create(estadoDemandaDTO)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
       }
    }
    
    @PUT
    public Response update(EstadoDemandaDTO estadoDemandaDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.update(estadoDemandaDTO)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
       }
    }
    
    @GET
    public Response list(@QueryParam("demandas") boolean demandas) {        
        BaseResponse baseResponse = new BaseResponse();
        try {
            return baseResponse.entity(service.list(demandas)).status(200).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }
    
    @GET()
    @Path("por-estados/{idUser}")
    public Response listPorEstados(@PathParam(value = "idUser") Long idUser) {        
        BaseResponse baseResponse = new BaseResponse();
        try {
            return baseResponse.entity(service.listPorEstados(idUser)).status(200).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

}
