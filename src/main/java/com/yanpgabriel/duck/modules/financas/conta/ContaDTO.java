package com.yanpgabriel.duck.modules.financas.conta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ContaDTO {

    private Long id;
    private String nome;
    private String instituicao;
    private String cor;
    private Boolean somarNaTelaInicial;
    private BigDecimal saldoInicial;
    private LocalDateTime dtCriacao;
    private LocalDateTime dtArquivacao;
    private Long idCategoria;
    private UUID idUsuario;

    public ContaDTO(ContaDTO contaDTO) {
        this.id = contaDTO.getId();
        this.nome = contaDTO.getNome();
        this.instituicao = contaDTO.getInstituicao();
        this.cor = contaDTO.getCor();
        this.somarNaTelaInicial = contaDTO.getSomarNaTelaInicial();
        this.saldoInicial = contaDTO.getSaldoInicial();
        this.dtCriacao = contaDTO.getDtCriacao();
        this.dtArquivacao = contaDTO.getDtArquivacao();
        this.idCategoria = contaDTO.getIdCategoria();
        this.idUsuario = contaDTO.getIdUsuario();
    }
}
