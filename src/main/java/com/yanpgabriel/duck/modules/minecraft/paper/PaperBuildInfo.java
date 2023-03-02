package com.yanpgabriel.duck.modules.minecraft.paper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PaperBuildInfo extends PaperVersionInfo {
    private String build;
    private PaperDownload downloads;
}
