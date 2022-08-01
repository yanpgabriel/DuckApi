package dev.yanpgabriel.duck.modules.auth;

import dev.yanpgabriel.duck.modules.user.UserEntity;
import dev.yanpgabriel.duck.modules.user.UserService;
import dev.yanpgabriel.duck.responses.BaseResponse;
import dev.yanpgabriel.duck.util.DuckApiConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Objects;

@ApplicationScoped
public class AuthService {

    @Inject
    UserService userService;

    @Inject
    DuckApiConfig duckApiConfig;
    
    public Response proccessValidation(String keycloackId) {
        BaseResponse baseResponse = new BaseResponse();
        UserEntity userEntity = userService.findByKeycloackId(keycloackId);
        if (Objects.isNull(userEntity)) {
            userEntity = userService.create(new UserEntity(keycloackId));
            baseResponse.setStatus(201);
            baseResponse.addExtras("USER_CREATED");
        }
        baseResponse.setEntity(userEntity);
        return baseResponse.toResponse();
    }
    
}
