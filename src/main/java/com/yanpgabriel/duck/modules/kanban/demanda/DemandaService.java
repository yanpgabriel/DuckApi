package com.yanpgabriel.duck.modules.kanban.demanda;

import com.yanpgabriel.duck.modules.kanban.estado_demanda.EstadoDemandaEntity;
import com.yanpgabriel.duck.modules.user.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DemandaService {
    
    @Inject
    DemandaMapper demandaMapper;

    @Transactional
    public void delete(Long idDemanda) {
        DemandaEntity.deleteById(idDemanda);
    }

    @Transactional
    public DemandaDTO create(DemandaDTO demandaDTO) {
        DemandaEntity demandaEntity = demandaMapper.toDemandaEntity(demandaDTO);
        try {
            demandaEntity.setUser(UserEntity.findById(demandaDTO.getIdUser()));
            demandaEntity.setEstado(EstadoDemandaEntity.findById(demandaDTO.getIdEstado()));
            DemandaEntity.persist(demandaEntity);
        } catch (Exception e) {
            throw new RuntimeException("Ja existe uma demanda sua nesse horario!");
        }
        return demandaMapper.toDemandaDTO(demandaEntity);
    }

    @Transactional
    public DemandaDTO update(DemandaDTO demandaDTO) {
        DemandaEntity demandaEntity = DemandaEntity.findById(demandaDTO.getId());
        demandaEntity.updateValues(demandaMapper.toDemandaEntity(demandaDTO));
        DemandaEntity.persist(demandaEntity);
        return demandaMapper.toDemandaDTO(demandaEntity);
    }
    
    public List<DemandaDTO> list() {
        List<DemandaEntity> demandas = DemandaEntity.listAll();
        return demandas.stream().map(demandaMapper::toDemandaDTO).collect(Collectors.toList());
    }
    
    public DemandaDTO get(Long idDemanda) {
        DemandaEntity demandaEntity = DemandaEntity.findById(idDemanda);
        return demandaMapper.toDemandaDTO(demandaEntity);
    }

}
