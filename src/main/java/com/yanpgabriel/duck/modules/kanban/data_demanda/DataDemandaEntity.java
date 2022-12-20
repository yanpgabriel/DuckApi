package com.yanpgabriel.duck.modules.kanban.data_demanda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "tb_data_demanda")
@Getter
@Setter
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class DataDemandaEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "data_demanda_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "data_demanda_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime de;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ate;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idDemanda", referencedColumnName = "id", nullable = false)
    private DemandaEntity demanda;

    public DataDemandaEntity updateValues(DataDemandaEntity dataDemandaEntity) {
        this.de = Objects.nonNull(dataDemandaEntity.de) ? dataDemandaEntity.de : this.de;
        this.ate = Objects.nonNull(dataDemandaEntity.ate) ? dataDemandaEntity.ate : this.ate;
        return this;
    }

}
