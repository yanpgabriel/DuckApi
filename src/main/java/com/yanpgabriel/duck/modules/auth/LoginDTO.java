package com.yanpgabriel.duck.modules.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "É necessario preencher e-mail para login!")
    private String email;
    @NotBlank(message = "É necessario preencher senha para login!")
    private String password;
    
}
