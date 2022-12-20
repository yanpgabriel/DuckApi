package com.yanpgabriel.duck.modules.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfileDTO {
    
    private Long id;
    private String name;
    private LocalDateTime dtcreation;
    private List<RoleDTO> roles;
    
}
