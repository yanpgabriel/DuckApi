package com.yanpgabriel.duck.modules.minecraft;

import com.yanpgabriel.duck.responses.BaseResponse;
import com.yanpgabriel.duck.responses.TypeResponse;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/minecraft/papermc")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MinecraftResource {

    @Inject
    MinecraftService service;

    @GET
    @Path("/version")
    @Authenticated
    public Response listPaperMcVersions() {
        BaseResponse baseResponse = BaseResponse.instaceSuccess();
        try {
            return baseResponse.status(200).entity(service.getListPaperMcVersions()).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

    @GET
    @Path("/version/{versao}")
    @Authenticated
    public Response listBuildsFromVersion(@PathParam("versao") String versao) {
        BaseResponse baseResponse = BaseResponse.instaceSuccess();
        try {
            return baseResponse.status(200).entity(service.getListBuildsFromVersion(versao)).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

    @GET
    @Path("/version/{versao}/build/{build}")
    @Authenticated
    public Response buildInfo(@PathParam("versao") String versao, @PathParam("build") String build) {
        BaseResponse baseResponse = BaseResponse.instaceSuccess();
        try {
            return baseResponse.status(200).entity(service.getInfoBuild(versao, build)).toResponse();
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

    @GET
    @Path("/version/{versao}/build/{build}/file")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Authenticated
    public Response fileFromBuild(@PathParam("versao") String versao, @PathParam("build") String build) {
        BaseResponse baseResponse = BaseResponse.instaceSuccess();
        try {
            return service.getFile(versao, build);
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

    @GET
    @Path("/version/{versao}/build/{build}/file-teste")
    @Authenticated
    public Response fileFromBuildteste(@PathParam("versao") String versao, @PathParam("build") String build) {
        BaseResponse baseResponse = BaseResponse.instaceSuccess();
        try {
            return service.getFileTeste(versao, build);
        } catch (Exception e) {
            return baseResponse.type(TypeResponse.SERVER_ERRO).status(500).extra(e.getMessage()).toResponse();
        }
    }

}
