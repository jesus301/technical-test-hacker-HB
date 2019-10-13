package com.hotelbeds.supplierintegrations.util;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheManagerImpl {

    private final CacheManager cacheManager;

    public CacheManagerImpl(final CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public <T> T get(final String nameCache, final String key, final Class<T> tClass) {
        Cache cache = cacheManager.getCache(nameCache);
        return cache != null
                ? cache.get(key, tClass)
                : null;
    }

    public void put(final String nameCache, final String key, final Object object) {
        Cache cache = cacheManager.getCache(nameCache);
        if (cache != null && object != null && key != null) {
            cache.put(key, object);
        }
    }
}
