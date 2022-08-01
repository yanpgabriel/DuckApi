package dev.yanpgabriel.duck.modules.kanban.data_demanda;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DataDemandaDTO {

    @Schema(nullable = true)
    private Long id;
    private Long idDemanda;
    private LocalDateTime de;
    private LocalDateTime ate;
    
}
