package com.yanpgabriel.duck.modules.financas.categoria;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoriaDTO {

    private Long id;
    private String descricao;
    private String icone;
    private String corHexadecimal;
    private LocalDateTime dtCriacao;
    private LocalDateTime dtExclusao;
    private TipoCategoriaEnum tipo;
    private Long idCategoriaPai;
    private Long idUsuario;

}
