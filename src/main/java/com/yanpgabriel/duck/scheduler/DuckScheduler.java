package com.yanpgabriel.duck.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

@ApplicationScoped
public class DuckScheduler {

//    @Scheduled(cron = "{duck.cron}")
    public void task() {
        System.out.println(LocalDateTime.now());
    }

}
