package com.yanpgabriel.duck.modules.financas.conta;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "tb_conta")
@Getter
@Setter
@NoArgsConstructor
public class ContaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "conta_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "conta_seq")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column()
    private String descricao;

    @Column(nullable = false)
    private String instituicao;

    @Column(nullable = false)
    private BigDecimal saldoInicial;

    @Column()
    private String cor;

    @Column()
    private Boolean somarNaTelaInicial;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtCriacao;

    @Column()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtArquivacao;

    @PrePersist
    public void prePersist() {
        this.dtCriacao = LocalDateTime.now();
        this.saldoInicial = new BigDecimal("0.00");
        this.somarNaTelaInicial = false;
    }
}
