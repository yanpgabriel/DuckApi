package dev.yanpgabriel.duck.modules.kanban.data_demanda;

import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {DemandaMapper.class}
)
public interface DataDemandaMapper {

    DataDemandaMapper INSTANCE = Mappers.getMapper( DataDemandaMapper.class );
    
    DataDemandaDTO toDataDemandaDTO(DataDemandaEntity dataDemanda);
    DataDemandaEntity toDataDemandaEntity(DataDemandaDTO dataDemanda);
    
    @AfterMapping
    default void afterToDataDemandaDTO(@MappingTarget DataDemandaDTO dataDemandaDTO, DataDemandaEntity dataDemandaEntity) {
        if (Objects.nonNull(dataDemandaEntity.getDemanda())) {
            dataDemandaDTO.setIdDemanda(dataDemandaEntity.getDemanda().getId());
        }
    }
    
}
