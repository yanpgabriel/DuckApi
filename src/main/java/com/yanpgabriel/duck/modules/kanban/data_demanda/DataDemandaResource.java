package com.yanpgabriel.duck.modules.kanban.data_demanda;

import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.responses.TypeResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/data-demanda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DataDemandaResource {
    
    @Inject
    DataDemandaService service;

    @DELETE
    @Path("{idDataDemanda}")
    public Response delete(@PathParam(value = "idDataDemanda") Long idDataDemanda) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            service.delete(idDataDemanda);
            return baseResponse.status(204).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }
    
    @POST
    public Response create(DataDemandaDTO dataDemandaDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.create(dataDemandaDTO)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
       }
    }
    
    @PUT
    public Response update(DataDemandaDTO dataDemandaDTO) {
        BaseResponse baseResponse = new BaseResponse();
        try {
           return baseResponse.entity(service.update(dataDemandaDTO)).status(201).toResponse();
       } catch (Exception e) {
           return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
       }
    }
    
    @GET()
    public Response list() {        
        BaseResponse baseResponse = new BaseResponse();
        try {
            return baseResponse.entity(service.list()).status(200).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

}
