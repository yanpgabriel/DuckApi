package dev.yanpgabriel.duck.modules.kanban.estado_demanda;

import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EstadoDemandaDTO {

    @Schema(nullable = true)
    private Long id;
    private String desc;
    private Integer ordem;
    private LocalDateTime dtCriacao;
    private List<DemandaEntity> demandas = new ArrayList<>();
    
}
