package com.yanpgabriel.duck.modules.kanban.data_demanda;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DataDemandaDTO {

    private Long id;
    private Long idDemanda;
    private LocalDateTime de;
    private LocalDateTime ate;
    
}
