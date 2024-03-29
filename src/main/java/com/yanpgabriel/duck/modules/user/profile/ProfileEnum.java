package com.yanpgabriel.duck.modules.user.profile;

public enum ProfileEnum {

    SUPER_ADMINISTRADOR("Super Administrador"),
    ADMINISTRADOR("Administrador"),
    USUARIO("Usuário");
    
    private String name;
    
    ProfileEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
