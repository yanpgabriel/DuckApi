package dev.yanpgabriel.duck.interceptors;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Collections;

@Provider
public class AuthInterceptor implements ContainerRequestFilter {

    @ConfigProperty(name = "duckapi.oauth.client-id")
    private String CLIENT_ID;
    
    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        String authorization = context.getHeaderString("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            String idTokenString = authorization.replace("Bearer ", "");
            try {
                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                        .setAudience(Collections.singletonList(CLIENT_ID))
                        .build();

                GoogleIdToken idToken = verifier.verify(idTokenString);
                if (idToken != null) {
                    return;
                }
            } catch (Exception e) {
                context.abortWith(Response.status(Response.Status.NOT_ACCEPTABLE).build());
            }
            return;
        }
        context.abortWith(Response.status(Response.Status.NOT_ACCEPTABLE).build());
    }
    
}
