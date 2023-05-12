package com.yanpgabriel.duck.modules.financas.conta;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContaSaldoDTO extends ContaDTO {

    private BigDecimal saldoAtual;
    public ContaSaldoDTO(BigDecimal saldoAtual, ContaEntity contaEntity) {
        super(ContaMapper.INSTANCE.toContaDTO(contaEntity));
        this.saldoAtual = saldoAtual;
    }
    public ContaSaldoDTO(Integer saldoAtual, ContaEntity contaEntity) {
        super(ContaMapper.INSTANCE.toContaDTO(contaEntity));
        this.saldoAtual = BigDecimal.valueOf(saldoAtual);
    }

}
