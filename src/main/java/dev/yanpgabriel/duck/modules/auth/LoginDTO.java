package dev.yanpgabriel.duck.modules.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "É necessario preencher e-mail para login!")
    private String email;
    @NotBlank(message = "É necessario preencher senha para login!")
    private String password;
    
}
