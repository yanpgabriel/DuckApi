package com.yanpgabriel.duck.modules.financas.categoria;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanpgabriel.duck.modules.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "tb_categoria")
@Getter
@Setter
@NoArgsConstructor
public class CategoriaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "categoria_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "categoria_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String icone;

    @Column(nullable = false)
    private String corHexadecimal;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtCriacao;

    @Column()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtExclusao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCategoriaEnum tipo;

    @ManyToOne()
    @JoinColumn(name = "id_categoria_pai")
    private CategoriaEntity categoriaPai;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private UserEntity usuario;

    @PrePersist
    public void prePersist() {
        this.dtCriacao = LocalDateTime.now();
    }
}
