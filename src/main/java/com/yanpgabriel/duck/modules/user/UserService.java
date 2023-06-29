package com.yanpgabriel.duck.modules.user;

import com.yanpgabriel.duck.modules.user.profile.ProfileEntity;
import com.yanpgabriel.duck.responses.BaseResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {
    
    @Inject
    UserMapper userMapper;

    @Inject
    JsonWebToken jsonWebToken;
    
    @Transactional
    public Response create(UserDTO userDTO) {
        try {
            var userEntity = userMapper.toUserEntity(userDTO);
            userEntity.persistAndFlush();
            userDTO.setId(userEntity.getId());
            return BaseResponse.instaceSuccess().status(201).entity(userDTO).extra("Usuario salvo com sucesso!").toResponse();
        } catch (Exception e) {
            return BaseResponse.instaceError().extra("Falha ao salvar usuario. Verifique os dados e tente novament.").toResponse();
        }
    }

    @Transactional
    public Response update(UserDTO userDTO) {
        UserEntity usuarioDoBanco = UserEntity.findById(userDTO.getId());
        usuarioDoBanco.setEmail(userDTO.getEmail());
        usuarioDoBanco.setFullname(userDTO.getFullname());
        if (Objects.nonNull(userDTO.getPassword())) {
            usuarioDoBanco.setPassword(userDTO.getPassword());
        }
        if (Objects.nonNull(userDTO.getProfile())) {
            usuarioDoBanco.setProfile(ProfileEntity.findById(userDTO.getProfile().getId()));
        } else {
            usuarioDoBanco.setProfile(null);
        }
        var usuarioSalvo = userMapper.toUserDTO(usuarioDoBanco);
        return BaseResponse
                .instaceSuccess()
                .status(200)
                .entity(usuarioSalvo)
                .extra("Usuario atualizado com sucesso!")
                .toResponse();
    }

    public UserEntity findByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        return UserEntity.find("email = :email", map).firstResult();
    }
    
    public UserDTO getById(Long idUser) {
        return userMapper.toUserDTO(UserEntity.findById(idUser));
    }

    public List<UserDTO> list(Page page, Sort sort) {
        return UserEntity.findAll(sort).page(page).list().stream().map(user -> userMapper.toUserDTO((UserEntity) user)).collect(Collectors.toList());
    }

    public UserDTO obterUsuarioLogado() {
        JsonNumber idUsuario = jsonWebToken.getClaim("idUsuario");
        return userMapper.toUserDTO(UserEntity.findById(idUsuario.longValue()));
    }

    @Transactional
    public Response delete(Long idUsuario) {
        UserEntity usuario = UserEntity.findById(idUsuario);
        if (Objects.isNull(usuario)) {
            return BaseResponse.instaceError().status(404).extra("Usuario não encontrado.").toResponse();
        }
        try {
            usuario.delete();
            return BaseResponse.instaceSuccess().status(200).extra("Usuario deletado com sucesso!").toResponse();
        } catch (Exception e) {
            return BaseResponse.instaceServerError().extra(e.getMessage()).toResponse();
        }
    }
}
