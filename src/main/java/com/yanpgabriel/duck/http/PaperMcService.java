package com.yanpgabriel.duck.http;

import com.yanpgabriel.duck.modules.minecraft.paper.PaperBuildInfo;
import com.yanpgabriel.duck.modules.minecraft.paper.PaperInfo;
import com.yanpgabriel.duck.modules.minecraft.paper.PaperVersionInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


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
