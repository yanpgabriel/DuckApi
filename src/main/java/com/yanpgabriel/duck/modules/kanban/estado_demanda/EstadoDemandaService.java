package com.yanpgabriel.duck.modules.kanban.estado_demanda;

import com.yanpgabriel.duck.modules.kanban.demanda.DemandaDTO;
import com.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import com.yanpgabriel.duck.modules.kanban.demanda.DemandaMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.Comparator;
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
        return estadoDemandaEntities.stream()
                .peek(estado -> {
                    if (!comDemandas) estado.setDemandas(Collections.emptyList());
                })
                .map(estado -> estadoDemandaMapper.toEstadoDemandaDTO(estado))
                .peek(estado -> estado.getDemandas().sort(Comparator.comparingInt(DemandaDTO::getOrdem)))
                .collect(Collectors.toList());
    }
    
    public Map<Long, List<DemandaDTO>> listPorEstados(Long idUser) {
        List<DemandaEntity> demandaEntities = DemandaEntity.list("idUser", idUser);
        List<DemandaDTO> demandas = demandaEntities.stream().map(demandaMapper::toDemandaDTO).collect(Collectors.toList());
        Map<Long, List<DemandaDTO>> demandasPorEstado = demandas.stream().collect(Collectors.groupingBy(DemandaDTO::getIdEstado));
        return demandasPorEstado;
    }
    
}
