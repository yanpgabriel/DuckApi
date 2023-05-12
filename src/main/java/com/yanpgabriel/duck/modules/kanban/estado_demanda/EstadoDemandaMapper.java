package com.yanpgabriel.duck.modules.kanban.estado_demanda;

import com.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {DemandaMapper.class}
)
public interface EstadoDemandaMapper {

    // EstadoDemandaDTO toEstadoDemandaDTO(EstadoDemandaEntity estadoDemanda, boolean comDemanda);
    EstadoDemandaEntity toEstadoDemandaEntity(EstadoDemandaDTO estadoDemanda);
    EstadoDemandaDTO toEstadoDemandaDTO(EstadoDemandaEntity estadoDemanda);

//    default EstadoDemandaDTO toEstadoDemandaDTO(EstadoDemandaEntity estadoDemanda) {
//        return toEstadoDemandaDTO(estadoDemanda, false);
//    }
    
//    @AfterMapping
//    default void toEstadoDemandaDTO(@MappingTarget EstadoDemandaDTO estadoDemandaDTO, EstadoDemandaEntity estadoDemandaEntity) {
//        if ( estadoDemanda == null ) {
//            return null;
//        }
//        EstadoDemandaDTO estadoDemandaDTO = new EstadoDemandaDTO();
//        estadoDemandaDTO.setId( estadoDemanda.getId() );
//        estadoDemandaDTO.setDesc( estadoDemanda.getDesc() );
//        estadoDemandaDTO.setOrdem( estadoDemanda.getOrdem() );
//        estadoDemandaDTO.setDtCriacao( estadoDemanda.getDtCriacao() );
//        List<DemandaEntity> list = estadoDemanda.getDemandas();
//        if ( list != null && comDemanda) {
//            estadoDemandaDTO.setDemandas( new ArrayList<>( list ) );
//        }
//        estadoDemandaDTO.getDemandas().sort(Comparator.comparingInt(DemandaDTO::getOrdem));
//    }
    
}
