package com.yanpgabriel.duck.modules.financas.transacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanpgabriel.duck.modules.financas.categoria.CategoriaEntity;
import com.yanpgabriel.duck.modules.financas.conta.ContaEntity;
import com.yanpgabriel.duck.modules.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "tb_transacao")
@Getter
@Setter
@NoArgsConstructor
public class TransacaoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "transacao_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "transacao_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Boolean ignorar = false;

    @Column(nullable = false)
    private Boolean efetivado = false;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtCriacao;

    @Column()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtExclusao;

    @Column()
    @Enumerated(EnumType.STRING)
    private TipoTransacaoEnum tipoTransacao;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta")
    private ContaEntity conta;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private UserEntity usuario;

    @PrePersist
    public void prePersist() {
        this.dtCriacao = LocalDateTime.now();
    }
}
