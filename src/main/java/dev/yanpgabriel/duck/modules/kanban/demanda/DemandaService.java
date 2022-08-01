package dev.yanpgabriel.duck.modules.kanban.demanda;

import dev.yanpgabriel.duck.modules.kanban.estado_demanda.EstadoDemandaEntity;
import dev.yanpgabriel.duck.modules.user.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DemandaService {

    @Transactional
    public void delete(Long idDemanda) {
        DemandaEntity.deleteById(idDemanda);
    }

    @Transactional
    public DemandaDTO create(DemandaDTO demandaDTO) {
        DemandaEntity demandaEntity = DemandaMapper.INSTANCE.toDemandaEntity(demandaDTO);
        try {
            demandaEntity.setUser(UserEntity.findById(demandaDTO.getIdUser()));
            demandaEntity.setEstado(EstadoDemandaEntity.findById(demandaDTO.getIdEstado()));
            DemandaEntity.persist(demandaEntity);
        } catch (Exception e) {
            throw new RuntimeException("Ja existe uma demanda sua nesse horario!");
        }
        return DemandaMapper.INSTANCE.toDemandaDTO(demandaEntity);
    }

    @Transactional
    public DemandaDTO update(DemandaDTO demandaDTO) {
        DemandaEntity demandaEntity = DemandaEntity.findById(demandaDTO.getId());
        demandaEntity.updateValues(DemandaMapper.INSTANCE.toDemandaEntity(demandaDTO));
        DemandaEntity.persist(demandaEntity);
        return DemandaMapper.INSTANCE.toDemandaDTO(demandaEntity);
    }
    
    public List<DemandaDTO> list() {
        List<DemandaEntity> demandas = DemandaEntity.listAll();
        return demandas.stream().map(DemandaMapper.INSTANCE::toDemandaDTO).collect(Collectors.toList());
    }
    
    public DemandaDTO get(Long idDemanda) {
        DemandaEntity demandaEntity = DemandaEntity.findById(idDemanda);
        return DemandaMapper.INSTANCE.toDemandaDTO(demandaEntity);
    }

}
