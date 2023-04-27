package com.yanpgabriel.duck.modules.auth;

import com.yanpgabriel.duck.exceptions.NaoAutorizadoException;
import com.yanpgabriel.duck.modules.user.UserDTO;
import com.yanpgabriel.duck.modules.user.UserEntity;
import com.yanpgabriel.duck.modules.user.UserMapper;
import com.yanpgabriel.duck.modules.user.UserService;
import com.yanpgabriel.duck.modules.user.profile.ProfileDTO;
import com.yanpgabriel.duck.modules.user.role.RoleDTO;
import com.yanpgabriel.duck.util.DuckJWT;
import com.yanpgabriel.duck.util.DuckJson;
import com.yanpgabriel.duck.util.config.DuckApiConfig;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.auth.principal.JWTParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthService {

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @Inject
    JWTParser parser;

    @Inject
    DuckApiConfig duckApiConfig;

    private final HashMap<String, Long> mapRefreshTokens = new HashMap<>();

    public AuthDTO login(LoginDTO loginDTO) {
        var userDTO = this.checarCredenciais(loginDTO);
        // Gerar tokens
        return this.generateTokens(userDTO);
    }

    public AuthDTO retoken(String uuid) {
        if (!mapRefreshTokens.containsKey(uuid)) {
            throw new NaoAutorizadoException("Token invalido!");
        }
        Long idUserByRefreshToken = mapRefreshTokens.get(uuid);
        UserDTO userDTO = userService.getById(idUserByRefreshToken);
        return this.generateTokens(userDTO);
    }

    private AuthDTO generateTokens(UserDTO userDTO) {
        var grupos = getGruposFromRoles(userDTO);
        var accessToken = createAccessToken(userDTO, grupos);
        // var refreshToken = DuckJWT.generateRefreshToken(Collections.singletonMap("uid", userDTO.getId()));
        var refreshToken = DuckJWT.generateRefreshToken();
        var authDTO = new AuthDTO(accessToken, refreshToken);
        mapRefreshTokens.put(refreshToken, userDTO.getId());
        return authDTO;
    }

    private String createAccessToken(UserDTO userDTO, HashSet<String> grupos) {
        var mapClaims = new HashMap<String, Object>();
        mapClaims.put("idUsuario", userDTO.getId());
        mapClaims.put("user", DuckJson.toMap(DuckJson.objectMapperIgnoreNullFields(), userDTO));
        return DuckJWT.generateAccessToken(mapClaims, grupos);
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
