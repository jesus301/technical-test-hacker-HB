package com.hotelbeds.supplierintegrations.service;

import com.hotelbeds.supplierintegrations.model.IpNumberOfAttempt;
import com.hotelbeds.supplierintegrations.util.CacheManagerImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.hotelbeds.supplierintegrations.constant.Constant.NAME_CACHE_IP_ATTEMPT;

@Component
public class ValidationIpErrorService {

    private final CacheManagerImpl cacheManagerImpl;
    private final TimeComparatorService timeComparatorService;

    public ValidationIpErrorService(final CacheManagerImpl cacheManagerImpl,
                                    final TimeComparatorService timeComparatorService) {
        this.cacheManagerImpl = cacheManagerImpl;
        this.timeComparatorService = timeComparatorService;
    }

    public IpNumberOfAttempt processNumberAttempt(final String ipErrorLogin, final LocalDateTime time) {
        IpNumberOfAttempt cacheObject = cacheManagerImpl.get(NAME_CACHE_IP_ATTEMPT, ipErrorLogin, IpNumberOfAttempt.class);
        if (cacheObject != null) {
            return processAttempt(cacheObject, time);
        }
        return buildAndSaveIpNumberOfAttempt(ipErrorLogin, time, 1);
    }

    private IpNumberOfAttempt processAttempt(final IpNumberOfAttempt cacheAttempt, final LocalDateTime time) {
        if (timeComparatorService.isMinusFiveMinuteDifferent(cacheAttempt.getFirstAttemptTimestamp(), time)) {
            return buildAndSaveIpNumberOfAttempt(cacheAttempt.getIpErrorLogin(), cacheAttempt.getFirstAttemptTimestamp(),
                    cacheAttempt.getNumberOfAttempt() + 1);
        }

        return buildAndSaveIpNumberOfAttempt(cacheAttempt.getIpErrorLogin(), time, 1);
    }

    private IpNumberOfAttempt buildAndSaveIpNumberOfAttempt(final String ipErrorLogin, final LocalDateTime hourFirstAttempt,
                                                            final Integer numberOfAttempt) {
        final IpNumberOfAttempt attempt = buildIpNumberOfAttempt(ipErrorLogin, hourFirstAttempt, numberOfAttempt);
        cacheManagerImpl.put(NAME_CACHE_IP_ATTEMPT, ipErrorLogin, attempt);
        return attempt;
    }

    private IpNumberOfAttempt buildIpNumberOfAttempt(final String ipErrorLogin, final LocalDateTime hourFirstAttempt,
                                                     final Integer numberOfAttempt) {
        return IpNumberOfAttempt.builder()
                .ipErrorLogin(ipErrorLogin)
                .firstAttemptTimestamp(hourFirstAttempt)
                .numberOfAttempt(numberOfAttempt)
                .build();
    }
}
