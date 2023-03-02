package com.yanpgabriel.duck.modules.apps;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AppDTO {

    private Long id;
    private String name;
    private String icon;
    private String link;
    private LocalDateTime dtcreation;

}
