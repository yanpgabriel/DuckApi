package dev.yanpgabriel.duck.modules.user;

import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {DemandaMapper.class, ProfileMapper.class}
)
public interface UserMapper {

    UserDTO toUserDTO(UserEntity userEntity);
    UserEntity toUserEntity(UserDTO userDTO);
    
}
