package dev.yanpgabriel.duck.modules.user;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {
    
    @Inject
    UserMapper userMapper;
    
    @Transactional
    public UserEntity create(UserEntity userEntity) {
        userEntity.persist();
        return userEntity;
    }

    public UserEntity findByKeycloackId(String googleTokenId) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", googleTokenId);
        return UserEntity.find("keycloackId = :token", map).firstResult();
    }

    public UserEntity findByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        return UserEntity.find("email = :email", map).firstResult();
    }
    
    public UserDTO get(Long idUser) {
        return userMapper.toUserDTO(UserEntity.findById(idUser));
    }

    public List<UserDTO> list(Page page, Sort sort) {
        return UserEntity.findAll(sort).page(page).list().stream().map(user -> userMapper.toUserDTO((UserEntity) user)).collect(Collectors.toList());
    }
}
