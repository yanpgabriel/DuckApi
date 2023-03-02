package com.yanpgabriel.duck.modules.apps;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface AppMapper {

    AppDTO toAppDTO(AppEntity appEntity);
    AppEntity toAppEntity(AppDTO appDTO);

}
