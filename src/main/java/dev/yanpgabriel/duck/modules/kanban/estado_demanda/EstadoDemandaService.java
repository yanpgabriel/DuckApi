package dev.yanpgabriel.duck.modules.kanban.estado_demanda;

import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaDTO;
import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class EstadoDemandaService {
    
    @Inject
    DemandaMapper demandaMapper;
    
    @Inject
    EstadoDemandaMapper estadoDemandaMapper;

    @Transactional
    public void delete(Long idEstado) {
        EstadoDemandaEntity.deleteById(idEstado);
    }

    @Transactional
    public EstadoDemandaDTO create(EstadoDemandaDTO estadoDemandaDTO) {
        EstadoDemandaEntity demandaEntity = estadoDemandaMapper.toEstadoDemandaEntity(estadoDemandaDTO);
        try {
            EstadoDemandaEntity.persist(demandaEntity);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao persistir");
        }
        return estadoDemandaMapper.toEstadoDemandaDTO(demandaEntity);
    }

    @Transactional
    public EstadoDemandaDTO update(EstadoDemandaDTO estadoDemandaDTO) {
        EstadoDemandaEntity estadoDemandaEntity = EstadoDemandaEntity.findById(estadoDemandaDTO.getId());
        estadoDemandaEntity.updateValues(estadoDemandaMapper.toEstadoDemandaEntity(estadoDemandaDTO));
        EstadoDemandaEntity.persist(estadoDemandaEntity);
        return estadoDemandaMapper.toEstadoDemandaDTO(estadoDemandaEntity);
    }
    
    public List<EstadoDemandaDTO> list(boolean comDemandas) {
        List<EstadoDemandaEntity> estadoDemandaEntities = EstadoDemandaEntity.listAll();
        return estadoDemandaEntities.stream().map(estado -> {
            return estadoDemandaMapper.toEstadoDemandaDTO(estado, comDemandas);
        }).collect(Collectors.toList());
    }
    
    public Map<Long, List<DemandaDTO>> listPorEstados(Long idUser) {
        List<DemandaEntity> demandaEntities = DemandaEntity.list("idUser", idUser);
        List<DemandaDTO> demandas = demandaEntities.stream().map(demandaMapper::toDemandaDTO).collect(Collectors.toList());
        Map<Long, List<DemandaDTO>> demandasPorEstado = demandas.stream().collect(Collectors.groupingBy(DemandaDTO::getIdEstado));
        return demandasPorEstado;
    }
    
}
