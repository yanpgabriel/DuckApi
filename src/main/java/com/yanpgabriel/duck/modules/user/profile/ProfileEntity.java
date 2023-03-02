package com.yanpgabriel.duck.modules.user.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanpgabriel.duck.modules.user.role.RoleEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "tb_profile")
public class ProfileEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "profile_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "profile_seq")
    private Long id;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDtcreation() {
        return dtcreation;
    }

    public void setDtcreation(LocalDateTime dtcreation) {
        this.dtcreation = dtcreation;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
