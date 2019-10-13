package com.hotelbeds.supplierintegrations.service;

import com.hotelbeds.supplierintegrations.hackertest.detector.HackerDetector;
import com.hotelbeds.supplierintegrations.model.IpNumberOfAttempt;
import com.hotelbeds.supplierintegrations.util.LoginResultOption;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.hotelbeds.supplierintegrations.util.DateUtil.parseEpochFormatToLocalDateTimeDefaultUTC;

@Service
public class HackerDetectorService implements HackerDetector {

    private final ValidationIpErrorService validationIpErrorService;

    public HackerDetectorService(final ValidationIpErrorService validationIpErrorService) {
        this.validationIpErrorService = validationIpErrorService;
    }

    @Override
    public String parseLine(final String line) {
        final String[] processLine = line.split(",");
        if (processLine.length < 4) {
            throw new RuntimeException("Incorrect format process line");
        }
        return LoginResultOption.SIGNIN_FAILURE.toString().equals(processLine[2])
                ? processIpMaxLimitAttempts(processLine)
                : null;
    }

    private String processIpMaxLimitAttempts(final String[] processLine) {
        LocalDateTime dateOfAttempt = parseEpochFormatToLocalDateTimeDefaultUTC(
                processLine[1], ZoneOffset.UTC);
        IpNumberOfAttempt numberOfAttemptInLastFiveMinutes = validationIpErrorService
                .processNumberAttempt(processLine[0], dateOfAttempt);
        return numberOfAttemptInLastFiveMinutes.getNumberOfAttempt() >= 5
                ? numberOfAttemptInLastFiveMinutes.getIpErrorLogin()
                : null;
    }
}
