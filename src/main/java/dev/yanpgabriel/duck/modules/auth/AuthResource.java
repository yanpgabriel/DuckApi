package dev.yanpgabriel.duck.modules.auth;

import dev.yanpgabriel.duck.responses.BaseResponse;
import dev.yanpgabriel.duck.responses.TypeResponse;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {
    
    @Inject
    AuthService service;
    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/login")
    public Response validade(@QueryParam("tokenId") String tokenId) {
        try {
            return service.proccessValidation(tokenId);
        } catch (Exception e) {
            return new BaseResponse().status(401).entity(Boolean.FALSE).extras("GOOGLE_TOKEN_INVALID").type(TypeResponse.ERROR).toResponse();
        }
    }
        @GET
    @Path("/check-token")
    @PermitAll
    public Response check() {
        return new BaseResponse().entity(jwt.getRawToken()).toResponse();
    }
    
}
