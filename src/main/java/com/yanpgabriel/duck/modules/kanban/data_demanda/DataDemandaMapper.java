package com.yanpgabriel.duck.modules.kanban.data_demanda;

import com.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {DemandaMapper.class}
)
public interface DataDemandaMapper {

    DataDemandaDTO toDataDemandaDTO(DataDemandaEntity dataDemanda);
    DataDemandaEntity toDataDemandaEntity(DataDemandaDTO dataDemanda);
    
    @AfterMapping
    default void afterToDataDemandaDTO(@MappingTarget DataDemandaDTO dataDemandaDTO, DataDemandaEntity dataDemandaEntity) {
        if (Objects.nonNull(dataDemandaEntity.getDemanda())) {
            dataDemandaDTO.setIdDemanda(dataDemandaEntity.getDemanda().getId());
        }
    }
    
}
