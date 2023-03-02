package com.yanpgabriel.duck.modules.minecraft.paper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PaperInfo extends PaperMinecraft {
    private List<String> version_groups;
    private List<String> versions;
}
