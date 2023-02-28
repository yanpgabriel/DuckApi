package com.yanpgabriel.duck.modules.auth;

import com.yanpgabriel.duck.exceptions.NaoAutorizadoException;
import com.yanpgabriel.duck.modules.user.*;
import com.yanpgabriel.duck.util.DuckApiConfig;
import com.yanpgabriel.duck.util.DuckJWT;
import com.yanpgabriel.duck.util.DuckJson;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthService {

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @Inject
    DuckApiConfig duckApiConfig;

    private HashMap<String, Long> mapTokens = new HashMap<>();

    public AuthDTO login(LoginDTO loginDTO) {
        var userDTO = this.checarCredenciais(loginDTO);
        // Gerar tokens
        return this.generateTokens(userDTO);
    }

    public AuthDTO retoken(String uuid) {
        if (!mapTokens.containsKey(uuid)) {
            throw new NaoAutorizadoException("Token invalido!");
        }
        Long idUserByRefreshToken = mapTokens.get(uuid);
        UserDTO userDTO = userService.getById(idUserByRefreshToken);
        return this.generateTokens(userDTO);
    }

    private AuthDTO generateTokens(UserDTO userDTO) {
        var grupos = getGruposFromRoles(userDTO);
        var accessToken = DuckJWT.generateAccessToken(Collections.singletonMap("user", DuckJson.toMap(DuckJson.objectMapperIgnoreNullFields(), userDTO)), grupos);
        // var refreshToken = DuckJWT.generateRefreshToken(Collections.singletonMap("uid", userDTO.getId()));
        var refreshToken = DuckJWT.generateRefreshToken();
        var authDTO = new AuthDTO(accessToken, refreshToken);
        mapTokens.put(refreshToken, userDTO.getId());
        return authDTO;
    }

    private HashSet<String> getGruposFromRoles(UserDTO userDTO) {
        var grupos = new HashSet<String>();
        ProfileDTO profileDTO = userDTO.getProfile();
        if (Objects.nonNull(profileDTO)) {
            Optional.of(profileDTO.getRoles()).ifPresent(roles -> grupos.addAll(roles.stream().map(RoleDTO::getName).collect(Collectors.toSet())));
        }
        return grupos;
    }

    private UserDTO checarCredenciais(LoginDTO loginDTO) {
        UserEntity userEntity = userService.findByEmail(loginDTO.getEmail());
        if (userEntity == null)
            throw new NaoAutorizadoException("Usuário não identificado!");
        var senhaCorreta = BcryptUtil.matches(loginDTO.getPassword(), userEntity.getPassword());
        if (!senhaCorreta)
            throw new NaoAutorizadoException("Senha não confere!");
        return userMapper.toUserDTO(userEntity);
    }
}
