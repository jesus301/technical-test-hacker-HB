package com.hotelbeds.supplierintegrations.constant;

public class Constant {

    private Constant() {
        // to avoid instances
    }

    public static final String NAME_CACHE_IP_ATTEMPT = "ipAttempt";
    public static final String BASE_API = "/api";
    public static final String FORMAT_PATTERN = "EEE, dd MMM yyyy hh:MM:ss Z";
    public static final String PATTERN_PARSE_LINE = "(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]),)(([0-9]+),)((SIGNIN_SUCCESS|SIGNIN_FAILURE){1},)([\\.a-zA-Z])+";
}
