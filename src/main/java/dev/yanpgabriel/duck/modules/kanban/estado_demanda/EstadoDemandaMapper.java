package dev.yanpgabriel.duck.modules.kanban.estado_demanda;

import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {DemandaMapper.class}
)
public interface EstadoDemandaMapper {

    EstadoDemandaMapper INSTANCE = Mappers.getMapper( EstadoDemandaMapper.class );
    
    // EstadoDemandaDTO toEstadoDemandaDTO(EstadoDemandaEntity estadoDemanda, boolean comDemanda);
    EstadoDemandaEntity toEstadoDemandaEntity(EstadoDemandaDTO estadoDemanda);

    default EstadoDemandaDTO toEstadoDemandaDTO(EstadoDemandaEntity estadoDemanda) {
        return toEstadoDemandaDTO(estadoDemanda, false);
    }
    
    // @AfterMapping
    default EstadoDemandaDTO toEstadoDemandaDTO(EstadoDemandaEntity estadoDemanda, boolean comDemanda) {
        if ( estadoDemanda == null ) {
            return null;
        }

        EstadoDemandaDTO estadoDemandaDTO = new EstadoDemandaDTO();

        estadoDemandaDTO.setId( estadoDemanda.getId() );
        estadoDemandaDTO.setDesc( estadoDemanda.getDesc() );
        estadoDemandaDTO.setOrdem( estadoDemanda.getOrdem() );
        estadoDemandaDTO.setDtCriacao( estadoDemanda.getDtCriacao() );
        List<DemandaEntity> list = estadoDemanda.getDemandas();
        if ( list != null && comDemanda) {
            estadoDemandaDTO.setDemandas( new ArrayList<>( list ) );
        }
        return estadoDemandaDTO;
    }
    
}
