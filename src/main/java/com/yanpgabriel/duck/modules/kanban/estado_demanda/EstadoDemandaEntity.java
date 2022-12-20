package com.yanpgabriel.duck.modules.kanban.estado_demanda;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "tb_estado_demanda")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class EstadoDemandaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "estado_demanda_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "estado_demanda_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String desc;

    @Column(nullable = false)
    private Integer ordem;

    @Column(nullable = false)
    private LocalDateTime dtCriacao;

    @OneToMany(targetEntity= DemandaEntity.class, mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DemandaEntity> demandas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dtCriacao = LocalDateTime.now();
    }

    public void addDemanda(DemandaEntity demandaEntity) {
        demandaEntity.setEstado(this);
        demandas.add(demandaEntity);
    }

    public EstadoDemandaEntity updateValues(EstadoDemandaEntity estadoDemandaEntity) {
        this.desc = Objects.nonNull(estadoDemandaEntity.desc) ? estadoDemandaEntity.desc : this.desc;
        this.ordem = Objects.nonNull(estadoDemandaEntity.ordem) ? estadoDemandaEntity.ordem : this.ordem;
        this.dtCriacao = Objects.nonNull(estadoDemandaEntity.dtCriacao) ? estadoDemandaEntity.dtCriacao : this.dtCriacao;
        return this;
    }
}
