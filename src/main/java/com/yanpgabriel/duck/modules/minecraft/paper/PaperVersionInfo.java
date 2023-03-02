package com.yanpgabriel.duck.modules.minecraft.paper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PaperVersionInfo extends PaperMinecraft {
    private String version;
    private List<String> builds;
}
