package dev.yanpgabriel.duck.modules.kanban.estado_demanda;

import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaDTO;
import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class EstadoDemandaService {

    @Transactional
    public void delete(Long idEstado) {
        EstadoDemandaEntity.deleteById(idEstado);
    }

    @Transactional
    public EstadoDemandaDTO create(EstadoDemandaDTO estadoDemandaDTO) {
        EstadoDemandaEntity demandaEntity = EstadoDemandaMapper.INSTANCE.toEstadoDemandaEntity(estadoDemandaDTO);
        try {
            EstadoDemandaEntity.persist(demandaEntity);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao persistir");
        }
        return EstadoDemandaMapper.INSTANCE.toEstadoDemandaDTO(demandaEntity);
    }

    @Transactional
    public EstadoDemandaDTO update(EstadoDemandaDTO estadoDemandaDTO) {
        EstadoDemandaEntity estadoDemandaEntity = EstadoDemandaEntity.findById(estadoDemandaDTO.getId());
        estadoDemandaEntity.updateValues(EstadoDemandaMapper.INSTANCE.toEstadoDemandaEntity(estadoDemandaDTO));
        EstadoDemandaEntity.persist(estadoDemandaEntity);
        return EstadoDemandaMapper.INSTANCE.toEstadoDemandaDTO(estadoDemandaEntity);
    }
    
    public List<EstadoDemandaDTO> list(boolean comDemandas) {
        List<EstadoDemandaEntity> estadoDemandaEntities = EstadoDemandaEntity.listAll();
        return estadoDemandaEntities.stream().map(estado -> {
            return EstadoDemandaMapper.INSTANCE.toEstadoDemandaDTO(estado, comDemandas);
        }).collect(Collectors.toList());
    }
    
    public Map<Long, List<DemandaDTO>> listPorEstados(Long idUser) {
        List<DemandaEntity> demandaEntities = DemandaEntity.list("idUser", idUser);
        List<DemandaDTO> demandas = demandaEntities.stream().map(DemandaMapper.INSTANCE::toDemandaDTO).collect(Collectors.toList());
        Map<Long, List<DemandaDTO>> demandasPorEstado = demandas.stream().collect(Collectors.groupingBy(DemandaDTO::getIdEstado));
        return demandasPorEstado;
    }
    
}
