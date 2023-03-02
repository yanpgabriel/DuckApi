package com.yanpgabriel.duck.modules.financas.conta;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ContaMapper {

    ContaDTO toContaDTO(ContaEntity contaEntity);
    ContaEntity toContaEntity(ContaDTO contaDTO);

}
