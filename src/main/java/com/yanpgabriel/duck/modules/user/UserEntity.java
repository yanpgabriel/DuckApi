package com.yanpgabriel.duck.modules.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yanpgabriel.duck.modules.kanban.demanda.DemandaEntity;
import com.yanpgabriel.duck.modules.user.profile.ProfileEntity;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tb_user")
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column
    private String fullname;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;

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

    @PrePersist
    public void prePersist() {
        this.dtcreation = LocalDateTime.now();
        if (StringUtils.isNotBlank(this.password)) {
            this.password = BcryptUtil.bcryptHash(this.password);
        }
    }
}
