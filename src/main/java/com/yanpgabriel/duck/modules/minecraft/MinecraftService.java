package com.yanpgabriel.duck.modules.minecraft;

import com.yanpgabriel.duck.http.PaperMcService;
import com.yanpgabriel.duck.responses.BaseResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class MinecraftService {

    @Inject
    @RestClient
    PaperMcService paperMcService;

    public List<String> getListPaperMcVersions() {
        return paperMcService.getPaperMcVersions().getVersions();
    }

    public List<String> getListBuildsFromVersion(String versao) {
        return paperMcService.getPaperMcBuildsFromVersion(versao).getBuilds();
    }

    public String getInfoBuild(String versao, String build) {
        return paperMcService.getPaperMcBuildInfo(versao, build).getDownloads().getApplication().getName();
    }

    public Response getFile(String versao, String build) {
        String fileName = getInfoBuild(versao, build);
        byte[] file = paperMcService.getPaperMcFileFromBuildAndVersion(versao, build, fileName);
        BaseResponse baseResponse = BaseResponse.instaceFile();
        baseResponse.header("Content-Disposition", "attachment;filename=" + fileName);
        baseResponse.header("Content-Length", file.length);
        baseResponse.entity(file);
        return baseResponse.toResponse();
    }

    public Response getFileTeste(String versao, String build) {
        String fileName = getInfoBuild(versao, build);
        BaseResponse baseResponse = BaseResponse.instaceSuccess();
        baseResponse.header("Content-Disposition", "attachment;filename=" + fileName);
        byte[] file = paperMcService.getPaperMcFileFromBuildAndVersion(versao, build, fileName);
        baseResponse.entity(file);
        return baseResponse.toResponse();
    }

}
