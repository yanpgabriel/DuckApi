package dev.yanpgabriel.duck.modules.kanban.data_demanda;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DataDemandaService {
    
    @Inject
    DataDemandaMapper dataDemandaMapper;

    @Transactional
    public void delete(Long idEstado) {
        DataDemandaEntity.deleteById(idEstado);
    }

    @Transactional
    public DataDemandaDTO create(DataDemandaDTO dataDemandaDTO) {
        DataDemandaEntity dataDemandaEntity = dataDemandaMapper.toDataDemandaEntity(dataDemandaDTO);
        try {
            DataDemandaEntity.persist(dataDemandaEntity);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao persistir");
        }
        return dataDemandaMapper.toDataDemandaDTO(dataDemandaEntity);
    }

    @Transactional
    public DataDemandaDTO update(DataDemandaDTO dataDemandaDTO) {
        DataDemandaEntity dataDemandaEntity = DataDemandaEntity.findById(dataDemandaDTO.getId());
        dataDemandaEntity.updateValues(dataDemandaMapper.toDataDemandaEntity(dataDemandaDTO));
        DataDemandaEntity.persist(dataDemandaEntity);
        return dataDemandaMapper.toDataDemandaDTO(dataDemandaEntity);
    }
    
    public List<DataDemandaDTO> list() {
        List<DataDemandaEntity> dataDemandaEntities = DataDemandaEntity.listAll();
        return dataDemandaEntities.stream().map(dataDemandaMapper::toDataDemandaDTO).collect(Collectors.toList());
    }

}
