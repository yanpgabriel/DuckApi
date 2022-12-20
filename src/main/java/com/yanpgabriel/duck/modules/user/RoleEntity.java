package com.yanpgabriel.duck.modules.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tb_role")
public class RoleEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "role_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_seq")
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToMany(mappedBy="roles")
    private List<ProfileEntity> profiles;

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

    public List<ProfileEntity> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileEntity> profiles) {
        this.profiles = profiles;
    }
}
