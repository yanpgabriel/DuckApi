package dev.yanpgabriel.duck.modules.user;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {UserMapper.class, RoleMapper.class}
)
public interface ProfileMapper {

    ProfileDTO toProfileDTO(ProfileEntity profileEntity);
    ProfileEntity toProfileEntity(ProfileDTO profileDTO);

}
