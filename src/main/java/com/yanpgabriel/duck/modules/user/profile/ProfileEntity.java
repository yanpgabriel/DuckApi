package com.yanpgabriel.duck.modules.user.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanpgabriel.duck.modules.user.role.RoleEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tb_profile")
public class ProfileEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtcreation;

    @ManyToMany
    @JoinTable(
            name="tb_profile_tb_role",
            joinColumns={ @JoinColumn(name="profile_id") },
            inverseJoinColumns={ @JoinColumn(name="role_id") }
    )
    private List<RoleEntity> roles;
    
    @PrePersist
    public void prePersist() {
        this.dtcreation = LocalDateTime.now();
    }

}
