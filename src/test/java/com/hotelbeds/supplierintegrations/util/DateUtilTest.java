package com.hotelbeds.supplierintegrations.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.hotelbeds.supplierintegrations.util.DateUtil.parseEpochFormatToLocalDateTimeDefaultUTC;
import static com.hotelbeds.supplierintegrations.util.DateUtil.parseTimeStampToLocalDateTimeDefaultUTC;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class DateUtilTest {

    @Test
    public void testParseEpochFormatToLocalDateTimeUTC() {
        LocalDateTime time = parseEpochFormatToLocalDateTimeDefaultUTC("133612947",
                ZoneOffset.UTC);

        assertThat(1974, is(time.getYear()));
        assertThat(3, is(time.getMonthValue()));
        assertThat(27, is(time.getDayOfMonth()));
        assertThat(10, is(time.getHour()));
        assertThat(42, is(time.getMinute()));
        assertThat(27, is(time.getSecond()));
    }

    @Test
    public void testParseEpochFormatToLocalDateTimeDefaultUTC() {
        LocalDateTime time = parseEpochFormatToLocalDateTimeDefaultUTC("133612947",
                null);

        assertThat(1974, is(time.getYear()));
        assertThat(3, is(time.getMonthValue()));
        assertThat(27, is(time.getDayOfMonth()));
        assertThat(10, is(time.getHour()));
        assertThat(42, is(time.getMinute()));
        assertThat(27, is(time.getSecond()));
    }

    @Test(expected = RuntimeException.class)
    public void testParseNullEpochFormatToLocalDateTimeUTC() {
        parseEpochFormatToLocalDateTimeDefaultUTC(null, ZoneOffset.UTC);
    }

    @Test(expected = RuntimeException.class)
    public void testParseNullEpochFormatToLocalDateTimeNullZoneUTC() {
        parseEpochFormatToLocalDateTimeDefaultUTC(null, null);
    }

    @Test
    public void testParseTimeStampToLocalDateUTC() {
        Timestamp time = new Timestamp(98765321);

        LocalDateTime result = parseTimeStampToLocalDateTimeDefaultUTC(time, ZoneOffset.UTC);

        assertThat(1970, is(result.getYear()));
        assertThat(1, is(result.getMonthValue()));
        assertThat(2, is(result.getDayOfMonth()));
        assertThat(3, is(result.getHour()));
        assertThat(26, is(result.getMinute()));
        assertThat(5, is(result.getSecond()));
    }

    @Test
    public void testParseTimeStampToLocalDateNullZone() {
        Timestamp time = new Timestamp(98765321);

        LocalDateTime result = parseTimeStampToLocalDateTimeDefaultUTC(time, null);

        assertThat(1970, is(result.getYear()));
        assertThat(1, is(result.getMonthValue()));
        assertThat(2, is(result.getDayOfMonth()));
        assertThat(3, is(result.getHour()));
        assertThat(26, is(result.getMinute()));
        assertThat(5, is(result.getSecond()));
    }

    @Test(expected = RuntimeException.class)
    public void testParseNullTimeStampToLocalDateUTC() {
        parseTimeStampToLocalDateTimeDefaultUTC(null, ZoneOffset.UTC);
    }

    @Test(expected = RuntimeException.class)
    public void testParseNullTimeStampToLocalDateNullZone() {
        parseTimeStampToLocalDateTimeDefaultUTC(null, null);
    }
}
