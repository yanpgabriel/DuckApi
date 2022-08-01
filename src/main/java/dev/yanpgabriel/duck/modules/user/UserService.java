package dev.yanpgabriel.duck.modules.user;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UserService {
    
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
}
