package com.hotelbeds.supplierintegrations.controller;

import com.hotelbeds.supplierintegrations.service.HackerDetectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class HackerDetectorControllerTest {

    @InjectMocks
    private HackerDetectorController hackerDetectorController;

    @Mock
    private HackerDetectorService hackerDetectorService;

    @Test
    public void testProcessLineOkLogin() {
        final String line = "215.2.125.225,1570952623,SIGNIN_SUCCESS,Will.Smith";

        given(hackerDetectorService.parseLine(anyString())).willReturn(null);

        assertNull(hackerDetectorController.processLineHackerDetector(line));
    }

    @Test
    public void testProcessLineErrorLoginNotDetectHacker() {
        final String line = "215.2.125.225,1570952623,SIGNIN_FAILURE,Will.Smith";

        given(hackerDetectorService.parseLine(anyString())).willReturn(null);

        assertNull(hackerDetectorController.processLineHackerDetector(line));
    }

    @Test
    public void testProcessLineErrorLoginDetectHacker() {
        final String line = "215.2.125.225,1570952623,SIGNIN_FAILURE,Will.Smith";
        String ip = "215.2.125.225";

        given(hackerDetectorService.parseLine(anyString())).willReturn(ip);

        assertEquals(ip, hackerDetectorController.processLineHackerDetector(line));
    }
}
