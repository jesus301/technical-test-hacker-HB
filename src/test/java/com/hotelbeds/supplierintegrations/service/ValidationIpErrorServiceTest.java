package com.hotelbeds.supplierintegrations.service;

import com.hotelbeds.supplierintegrations.model.IpNumberOfAttempt;
import com.hotelbeds.supplierintegrations.util.CacheManagerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class ValidationIpErrorServiceTest {

    @InjectMocks
    private ValidationIpErrorService validationIpErrorService;

    @Mock
    private CacheManagerImpl cacheManager;
    @Mock
    private TimeComparatorService timeComparatorService;

    private static final String IP_ERROR_LOGIN = "80.238.9.179";

    @Test
    public void testFirstAttemptErrorLogin() {
        LocalDateTime time = LocalDateTime.now();
        given(cacheManager.get(anyString(), anyString(), any())).willReturn(null);
        doNothing().when(cacheManager).put(anyString(), anyString(), anyObject());

        IpNumberOfAttempt ipNumberOfAttempt = validationIpErrorService.processNumberAttempt(IP_ERROR_LOGIN, time);

        assertThat(IP_ERROR_LOGIN, is(ipNumberOfAttempt.getIpErrorLogin()));
        assertThat(time, is(ipNumberOfAttempt.getFirstAttemptTimestamp()));
        assertThat(1, is(ipNumberOfAttempt.getNumberOfAttempt()));
    }

    @Test
    public void testAttemptErrorInMoreFiveMinute() {
        LocalDateTime time = LocalDateTime.now();
        Integer numberOfAttempt = 4;
        given(cacheManager.get(anyString(), anyString(), any()))
                .willReturn(IpNumberOfAttempt.builder()
                        .ipErrorLogin(IP_ERROR_LOGIN)
                        .firstAttemptTimestamp(time)
                        .numberOfAttempt(numberOfAttempt)
                        .build());
        doNothing().when(cacheManager).put(anyString(), anyString(), anyObject());
        given(timeComparatorService.isMinusFiveMinuteDifferent(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(false);

        IpNumberOfAttempt ipNumberOfAttempt = validationIpErrorService.processNumberAttempt(IP_ERROR_LOGIN, time);

        assertThat(IP_ERROR_LOGIN, is(ipNumberOfAttempt.getIpErrorLogin()));
        assertThat(time, is(ipNumberOfAttempt.getFirstAttemptTimestamp()));
        assertThat(1, is(ipNumberOfAttempt.getNumberOfAttempt()));
    }

    @Test
    public void testAttemptErrorInFiveMinute() {
        LocalDateTime time = LocalDateTime.now();
        Integer numberOfAttempt = 2;
        given(cacheManager.get(anyString(), anyString(), any()))
                .willReturn(IpNumberOfAttempt.builder()
                        .ipErrorLogin(IP_ERROR_LOGIN)
                        .firstAttemptTimestamp(time)
                        .numberOfAttempt(numberOfAttempt)
                        .build());
        doNothing().when(cacheManager).put(anyString(), anyString(), anyObject());
        given(timeComparatorService.isMinusFiveMinuteDifferent(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(true);

        IpNumberOfAttempt ipNumberOfAttempt = validationIpErrorService.processNumberAttempt(IP_ERROR_LOGIN, time);

        assertThat(IP_ERROR_LOGIN, is(ipNumberOfAttempt.getIpErrorLogin()));
        assertThat(time, is(ipNumberOfAttempt.getFirstAttemptTimestamp()));
        assertThat(numberOfAttempt + 1, is(ipNumberOfAttempt.getNumberOfAttempt()));
    }
}
