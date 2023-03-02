package com.yanpgabriel.duck.modules.financas.conta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ContaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String instituicao;
    private BigDecimal saldo;
    private String cor;
    private Boolean somarNaTelaInicial;
    private LocalDateTime dtCriacao;
    private LocalDateTime dtArquivacao;

}
