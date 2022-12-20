package com.yanpgabriel.duck.modules.user;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {ProfileMapper.class}
)
public interface RoleMapper {

    RoleDTO toRoleDTO(RoleEntity roleEntity);
    RoleEntity toRoleEntity(RoleDTO roleDTO);

}
