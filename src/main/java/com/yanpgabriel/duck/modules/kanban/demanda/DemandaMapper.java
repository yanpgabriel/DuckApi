package com.yanpgabriel.duck.modules.kanban.demanda;

import com.yanpgabriel.duck.modules.kanban.data_demanda.DataDemandaMapper;
import com.yanpgabriel.duck.modules.kanban.estado_demanda.EstadoDemandaEntity;
import com.yanpgabriel.duck.modules.kanban.estado_demanda.EstadoDemandaMapper;
import com.yanpgabriel.duck.modules.user.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {DataDemandaMapper.class, EstadoDemandaMapper.class}
)
public interface DemandaMapper {

    DemandaDTO toDemandaDTO(DemandaEntity demanda);
    DemandaEntity  toDemandaEntity(DemandaDTO demanda);
    
    @AfterMapping
    default void afterToDemandaDTO(@MappingTarget DemandaDTO demandaDTO, DemandaEntity demandaEntity) {
        if (Objects.nonNull(demandaEntity.getUser())) {
            demandaDTO.setIdUser(demandaEntity.getUser().getId());
        }
        if (Objects.nonNull(demandaEntity.getEstado())) {
            demandaDTO.setIdEstado(demandaEntity.getEstado().getId());
        }
    }
    
    @AfterMapping
    default void afterToDemandaEntity(DemandaDTO demandaDTO, @MappingTarget DemandaEntity demandaEntity) {
        if (Objects.nonNull(demandaDTO.getIdUser())) {
            demandaEntity.setUser(UserEntity.findById(demandaDTO.getIdUser()));
        }
        if (Objects.nonNull(demandaDTO.getIdEstado())) {
            demandaEntity.setEstado(EstadoDemandaEntity.findById(demandaDTO.getIdEstado()));
        }
    }

}
