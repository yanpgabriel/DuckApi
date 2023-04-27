package com.yanpgabriel.duck.modules.financas.conta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanpgabriel.duck.modules.financas.categoria.CategoriaEntity;
import com.yanpgabriel.duck.modules.financas.transacao.TransacaoEntity;
import com.yanpgabriel.duck.modules.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_conta")
@Getter
@Setter
@NoArgsConstructor
public class ContaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "conta_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "conta_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nome;

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

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private UserEntity usuario;

    @OneToMany(mappedBy = "conta")
    private List<TransacaoEntity> receitas = new ArrayList<>();;

    @PrePersist
    public void prePersist() {
        this.dtCriacao = LocalDateTime.now();
        this.saldoInicial = new BigDecimal("0.00");
        this.somarNaTelaInicial = false;
    }

    public void addReceitas(TransacaoEntity transacaoEntity) {
        transacaoEntity.setConta(this);
        receitas.add(transacaoEntity);
    }
}
