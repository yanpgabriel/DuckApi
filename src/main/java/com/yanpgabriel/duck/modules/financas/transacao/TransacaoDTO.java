package com.yanpgabriel.duck.modules.financas.transacao;

import com.yanpgabriel.duck.modules.financas.categoria.CategoriaDTO;
import com.yanpgabriel.duck.modules.financas.conta.ContaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransacaoDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private Boolean ignorar = false;
    private LocalDateTime dtCriacao;
    private LocalDateTime dtExclusao;
    private Long idCategoria;
    private Long idConta;
    private Long idUsuario;

    private CategoriaDTO categoria;
    private ContaDTO conta;

}
