package com.yanpgabriel.duck.modules.user.role;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class RoleDTO {

    private UUID id;
    private String name;

}
