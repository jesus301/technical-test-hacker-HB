package com.hotelbeds.supplierintegrations.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.hotelbeds.supplierintegrations.util.DateUtil.parseTimeStampToLocalDateTimeDefaultUTC;

@Service
@Slf4j
public class TimeComparatorService {

    public int compareTwoTimeStamp(final Timestamp timestamp1, final Timestamp timestamp2) {
        LocalDateTime time1 = parseTimeStampToLocalDateTimeDefaultUTC(timestamp1,
                ZoneOffset.UTC);
        LocalDateTime time2 = parseTimeStampToLocalDateTimeDefaultUTC(timestamp2,
                ZoneOffset.UTC);

        return Math.toIntExact(Duration.between(time1, time2).toMinutes());
    }

    public boolean isMinusFiveMinuteDifferent(final LocalDateTime time, final LocalDateTime time2) {
        Duration duration = Duration.between(time, time2);
        return duration.getSeconds() < 300;
    }
}
