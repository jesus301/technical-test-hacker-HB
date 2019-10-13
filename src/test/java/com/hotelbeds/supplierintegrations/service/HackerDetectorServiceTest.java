package com.hotelbeds.supplierintegrations.service;

import com.hotelbeds.supplierintegrations.model.IpNumberOfAttempt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class HackerDetectorServiceTest {

    @InjectMocks
    private HackerDetectorService hackerDetectorService;

    @Mock
    private ValidationIpErrorService validationIpErrorService;

    private static final String PARSE_LINE_SIGNING_SUCCESS = "80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith";
    private static final String PARSE_LINE_SIGNING_FAILURE = "80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith";
    private static final String IP_ERROR_LOGIN = "80.238.9.179";

    @Test(expected = RuntimeException.class)
    public void testFormatLineError() {
        hackerDetectorService.parseLine("prueba");
    }

    @Test
    public void testCorrectLogin() {
        assertNull(hackerDetectorService.parseLine(PARSE_LINE_SIGNING_SUCCESS));
    }

    @Test(expected = RuntimeException.class)
    public void testFailedLoginTimeFormatError() {
        hackerDetectorService.parseLine("80.238.9.179,null,SIGNIN_FAILURE,Will.Smith");
    }

    @Test
    public void testFailedLoginOneTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        given(validationIpErrorService.processNumberAttempt(anyString(), any(LocalDateTime.class)))
                .willReturn(IpNumberOfAttempt.builder()
                    .ipErrorLogin(IP_ERROR_LOGIN)
                    .firstAttemptTimestamp(localDateTime)
                    .numberOfAttempt(1)
                    .build());
        assertNull(hackerDetectorService.parseLine(PARSE_LINE_SIGNING_FAILURE));
    }

    @Test
    public void testFailedLoginFiveOrMoreTimesInFiveMinute() {
        LocalDateTime localDateTime = LocalDateTime.now();
        given(validationIpErrorService.processNumberAttempt(anyString(), any(LocalDateTime.class)))
                .willReturn(IpNumberOfAttempt.builder()
                        .ipErrorLogin(IP_ERROR_LOGIN)
                        .firstAttemptTimestamp(localDateTime)
                        .numberOfAttempt(5)
                        .build());
        assertThat(IP_ERROR_LOGIN, is(hackerDetectorService.parseLine(PARSE_LINE_SIGNING_FAILURE)));
    }

}
