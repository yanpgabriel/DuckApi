package com.yanpgabriel.duck.modules.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {
    
    private String accessToken;
    private String refreshToken;
    
}
