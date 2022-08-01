package dev.yanpgabriel.duck.modules.kanban.demanda;

import dev.yanpgabriel.duck.modules.kanban.data_demanda.DataDemandaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class DemandaDTO {

    @Schema(nullable = true)
    private Long id;
    private Long idUser;
    private Long idEstado;
    private String titulo;
    private String desc;
    private Integer estimativa;
    private LocalDateTime dtCriacao;
    private List<DataDemandaDTO> datasDemanda;
    
}
