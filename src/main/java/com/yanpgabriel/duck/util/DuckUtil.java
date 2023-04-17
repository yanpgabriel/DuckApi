package com.yanpgabriel.duck.util;

import io.quarkus.panache.common.Sort;
import org.apache.commons.lang3.StringUtils;

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

    public static Sort makeSort(String sortQuery) {
        return StringUtils.isNotBlank(sortQuery) ? Sort.by(sortQuery) : null;
    }
}
