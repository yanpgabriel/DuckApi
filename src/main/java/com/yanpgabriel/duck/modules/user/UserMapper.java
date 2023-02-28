package com.yanpgabriel.duck.modules.user;

import com.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {DemandaMapper.class, ProfileMapper.class}
)
public interface UserMapper {

    UserDTO toUserDTO(UserEntity userEntity);
    UserEntity toUserEntity(UserDTO userDTO);

    @AfterMapping
    default void afterToUserDTO(@MappingTarget UserDTO dto, UserEntity entity) {
        // Obfuscated password
        dto.setPassword(null);
    }

}
