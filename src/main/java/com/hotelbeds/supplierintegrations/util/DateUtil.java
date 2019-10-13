package com.hotelbeds.supplierintegrations.util;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    public static LocalDateTime parseEpochFormatToLocalDateTimeDefaultUTC(final String epoch, final ZoneOffset zoneOffset) {
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(epoch)),
                    zoneOffset != null ? zoneOffset : ZoneOffset.UTC);
        } catch (NumberFormatException | DateTimeException exc) {
            throw new RuntimeException("Incorrect format time of process line", exc);
        }
    }

    public static LocalDateTime parseTimeStampToLocalDateTimeDefaultUTC(final Timestamp time, final ZoneOffset zoneOffset) {
        if (time == null) {
            throw new RuntimeException("Error parsing timestamp to LocalDateTime");
        }
        return time.toInstant()
                .atZone(zoneOffset != null ? zoneOffset : ZoneOffset.UTC)
                .toLocalDateTime();
    }
}
