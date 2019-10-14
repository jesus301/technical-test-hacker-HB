package com.hotelbeds.supplierintegrations.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TimeComparatorServiceTest {

    @InjectMocks
    private TimeComparatorService timeComparatorService;

    @Test
    public void testCompareSameTwoTimeStamp() {
        Timestamp timestamp = new Timestamp(987654321);

        Integer result = timeComparatorService.compareTwoTimeStamp(timestamp, timestamp);

        assertThat(0, is(result));
    }

    @Test
    public void testCompareTime1PlusTime2() {
        Timestamp timestamp1 = new Timestamp(987654321);
        Timestamp timestamp2 = new Timestamp(987999654);

        Integer result = timeComparatorService.compareTwoTimeStamp(timestamp1, timestamp2);

        assertThat(5, is(result));
    }

    @Test
    public void testCompareTime2PlusTime1() {
        Timestamp timestamp1 = new Timestamp(987999654);
        Timestamp timestamp2 = new Timestamp(987654321);

        Integer result = timeComparatorService.compareTwoTimeStamp(timestamp1, timestamp2);

        assertThat(-5, is(result));
    }

    @Test
    public void testTrueDifferentTimeMinusFiveMinute() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowMoreTwoMinutes = now.plusSeconds(120);
        assertTrue(timeComparatorService.isMinusFiveMinuteDifferent(now, nowMoreTwoMinutes));
    }

    @Test
    public void testFalseDifferentTimeMinusFiveMinute() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowMoreTwoMinutes = now.plusSeconds(301);
        assertFalse(timeComparatorService.isMinusFiveMinuteDifferent(now, nowMoreTwoMinutes));
    }
}
