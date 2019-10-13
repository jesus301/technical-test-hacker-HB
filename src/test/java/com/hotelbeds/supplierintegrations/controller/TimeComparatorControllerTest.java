package com.hotelbeds.supplierintegrations.controller;

import com.hotelbeds.supplierintegrations.model.TimeComparatorDTO;
import com.hotelbeds.supplierintegrations.service.TimeComparatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class TimeComparatorControllerTest {

    @InjectMocks
    private TimeComparatorController timeComparator;

    @Mock
    private TimeComparatorService timeComparatorService;

    @Test
    public void testSameResultCompareTwoDate() {
        Timestamp time1 = new Timestamp(987654321);
        given(timeComparatorService.compareTwoTimeStamp(any(Timestamp.class), any(Timestamp.class)))
                .willReturn(0);

        Integer result = timeComparator.timeComparator(TimeComparatorDTO.builder()
                            .time1(time1)
                            .time2(time1)
                            .build());
        assertThat(0, is(result));
    }

    @Test
    public void testNegativeResultCompareTwoDate() {
        Timestamp time1 = new Timestamp(987654321);
        Timestamp time2 = new Timestamp(987654559);
        given(timeComparatorService.compareTwoTimeStamp(any(Timestamp.class), any(Timestamp.class)))
                .willReturn(-123);

        Integer result = timeComparator.timeComparator(TimeComparatorDTO.builder()
                .time1(time1)
                .time2(time2)
                .build());
        assertThat(-123, is(result));
    }

    @Test
    public void testPositiveResultCompareTwoDate() {
        Timestamp time1 = new Timestamp(987654559);
        Timestamp time2 = new Timestamp(987654321);
        given(timeComparatorService.compareTwoTimeStamp(any(Timestamp.class), any(Timestamp.class)))
                .willReturn(123);

        Integer result = timeComparator.timeComparator(TimeComparatorDTO.builder()
                .time1(time1)
                .time2(time2)
                .build());
        assertThat(123, is(result));
    }
}
