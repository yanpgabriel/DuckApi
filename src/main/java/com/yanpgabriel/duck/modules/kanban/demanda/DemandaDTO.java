package com.yanpgabriel.duck.modules.kanban.demanda;

import com.yanpgabriel.duck.modules.kanban.data_demanda.DataDemandaDTO;
import com.yanpgabriel.duck.modules.kanban.estado_demanda.EstadoDemandaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class DemandaDTO {

    private Long id;
    @NotNull(message = "Você deve vincular um usuario a essa demanda.")
    private Long idUser;
    @NotNull(message = "A demanda precisa ter um estado.")
    private Long idEstado;
    private EstadoDemandaDTO estadoDemanda;
    @NotBlank(message = "A demanda precisa ter um titulo.")
    private String titulo;
    private String desc;
    @NotNull(message = "A demanda precisa ter uma estimativa.")
    private Integer estimativa;
    private Integer ordem;
    private LocalDateTime dtCriacao;
    private List<DataDemandaDTO> datasDemanda;
    
}
