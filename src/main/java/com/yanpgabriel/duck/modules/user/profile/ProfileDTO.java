package com.yanpgabriel.duck.modules.user.profile;

import com.yanpgabriel.duck.modules.user.role.RoleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProfileDTO {
    
    private UUID id;
    private String name;
    private LocalDateTime dtcreation;
    private List<RoleDTO> roles;
    
}
