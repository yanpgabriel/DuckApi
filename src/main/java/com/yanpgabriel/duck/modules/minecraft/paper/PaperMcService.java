package com.yanpgabriel.duck.modules.minecraft.paper;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey="paper-mc")
public interface PaperMcService {

    @GET
    PaperInfo getPaperMcVersions();

    @GET
    @Path("/versions/{versao}")
    PaperVersionInfo getPaperMcBuildsFromVersion(@PathParam("versao") String versao);

    @GET
    @Path("/versions/{versao}/builds/{build}")
    PaperBuildInfo getPaperMcBuildInfo(@PathParam("versao") String versao, @PathParam("build") String build);

    @GET
    @Path("/versions/{versao}/builds/{build}/downloads/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    byte[] getPaperMcFileFromBuildAndVersion(@PathParam("versao") String versao, @PathParam("build") String build, @PathParam("filename") String filename);

}
