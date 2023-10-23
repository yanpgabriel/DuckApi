package com.yanpgabriel.duck.modules.user.role;

import com.yanpgabriel.duck.modules.user.profile.ProfileEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tb_role")
public class RoleEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToMany(mappedBy="roles")
    private List<ProfileEntity> profiles;

}
