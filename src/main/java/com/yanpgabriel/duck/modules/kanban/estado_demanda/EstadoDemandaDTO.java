package com.yanpgabriel.duck.modules.kanban.estado_demanda;

import com.yanpgabriel.duck.modules.kanban.demanda.DemandaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EstadoDemandaDTO {

    private Long id;
    private String desc;
    private Integer ordem;
    private LocalDateTime dtCriacao;
    private List<DemandaDTO> demandas = new ArrayList<>();
    
}
