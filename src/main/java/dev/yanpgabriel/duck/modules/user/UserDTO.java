package dev.yanpgabriel.duck.modules.user;

import dev.yanpgabriel.duck.modules.kanban.demanda.DemandaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String keycloackId;
    private String fullname;
    private String email;
    private String password;
    private ProfileDTO profile;
    private LocalDateTime dtcreation;
    private List<DemandaDTO> demandaDTOs;
    
}
