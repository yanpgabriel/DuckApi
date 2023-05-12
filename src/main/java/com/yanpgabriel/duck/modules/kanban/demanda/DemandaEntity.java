package com.yanpgabriel.duck.modules.kanban.demanda;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yanpgabriel.duck.modules.kanban.data_demanda.DataDemandaEntity;
import com.yanpgabriel.duck.modules.kanban.estado_demanda.EstadoDemandaEntity;
import com.yanpgabriel.duck.modules.user.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "tb_demanda")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class DemandaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "demanda_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "demanda_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, name = "title")
    private String titulo;

    @Column(name = "description")
    private String desc;

    @Column(nullable = false)
    private Integer estimativa = 0;

    @Column(nullable = false)
    private LocalDateTime dtCriacao;

    @Column(nullable = false)
    private Integer ordem;
    
    @OneToMany(targetEntity= DataDemandaEntity.class, mappedBy = "demanda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DataDemandaEntity> datasDemanda = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idEstado", referencedColumnName = "id", nullable = false)
    private EstadoDemandaEntity estado;

    @PrePersist
    public void prePersist() {
        this.dtCriacao = LocalDateTime.now();
    }

    public void addDatasDemanda(DataDemandaEntity demandaEntity) {
        demandaEntity.setDemanda(this);
        datasDemanda.add(demandaEntity);
    }

    public DemandaEntity updateValues(DemandaEntity demandaEntity) {
        this.titulo = Objects.nonNull(demandaEntity.titulo) ? demandaEntity.titulo : this.titulo;
        this.user = Objects.nonNull(demandaEntity.user) ? demandaEntity.user : this.user;
        this.desc = Objects.nonNull(demandaEntity.desc) ? demandaEntity.desc : this.desc;
        this.estimativa = Objects.nonNull(demandaEntity.estimativa) ? demandaEntity.estimativa : this.estimativa;
        this.dtCriacao = Objects.nonNull(demandaEntity.dtCriacao) ? demandaEntity.dtCriacao : this.dtCriacao;
        this.estado = Objects.nonNull(demandaEntity.estado) ? demandaEntity.estado : this.estado;
        this.ordem = Objects.nonNull(demandaEntity.ordem) ? demandaEntity.ordem : this.ordem;
        return this;
    }
}
