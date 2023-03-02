package com.yanpgabriel.duck.modules.apps;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tb_app")
@Getter
@Setter
@NoArgsConstructor
public class AppEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "app_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "app_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dtcreation;

    @PrePersist
    public void prePersist() {
        this.dtcreation = LocalDateTime.now();
    }

}
