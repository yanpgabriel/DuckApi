package dev.yanpgabriel.duck.modules.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq")
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String keycloackId;
    
    @Column
    private String fullname;
    
    @Column
    private String email;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private ProfileEntity profile;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtcreation;

    @JsonIgnore
    @OneToMany(targetEntity= DemandaEntity.class, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DemandaEntity> demandas = new ArrayList<>();

    public UserEntity(String keycloackId) {
        this.profile = ProfileEntity.find("name = 'Usuário'").firstResult();
        this.keycloackId = keycloackId;
    }

    @PrePersist
    public void prePersist() {
        this.dtcreation = LocalDateTime.now();
    }

    public Map<String, Object> toObjectMap() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.convertValue(this, Map.class);
    }
}
