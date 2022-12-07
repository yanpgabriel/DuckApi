package dev.yanpgabriel.duck.util;

import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DuckJWT {
    
    public static String generateAccessToken(Map<String, Object> claims, Set<String> groups) {
        return Jwt
                .claims(claims)
                .groups(groups)
                .subject("DuckAPI-Access")
                .audience("DuckApps")
                .issuer("DuckAPI-Auth")
                .expiresIn(Duration.ofMinutes(5))
                .sign();
    }

    public static String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }
    // public static String generateRefreshToken(Map<String, Object> claims) {
    //     return  Jwt
    //              .claims(claims)
    //              .audience("DuckApps")
    //              .subject("DuckAPI-Refresh")
    //              .issuer("DuckAPI-Auth")
    //              .expiresIn(Duration.ofDays(1))
    //              .sign();
    // }
    //
}
