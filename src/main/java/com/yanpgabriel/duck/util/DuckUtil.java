package com.yanpgabriel.duck.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

public class DuckUtil {

    public static Long timestampFromLDT() {
        return DuckUtil.timestampFromLDT(LocalDateTime.now());
    }

    public static Long timestampFromLDT(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(-3)).getLong(ChronoField.INSTANT_SECONDS);
    }
}
