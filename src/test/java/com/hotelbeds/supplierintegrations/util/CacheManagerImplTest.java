package com.hotelbeds.supplierintegrations.util;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class CacheManagerImplTest {

    @InjectMocks
    private CacheManagerImpl cacheManagerImpl;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Ticker ticker;

    @Mock
    private CaffeineCache cache;

    @Before
    public void setUp() {
        Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
                .expireAfterWrite(200, TimeUnit.SECONDS)
                .ticker(ticker);
        cache = new CaffeineCache("testCache", caffeineBuilder.build());
        cache.put("1", "Object in cache");
    }

    @Test
    public void testGetCacheObject() {
        given(cacheManager.getCache(anyString())).willReturn(cache);

        String result = cacheManagerImpl.get("testCache", "1", String.class);

        assertThat("Object in cache", is(result));
    }

    @Test
    public void testNotExistCache() {
        given(cacheManager.getCache(anyString())).willReturn(null);

        assertNull(cacheManagerImpl.get("test", "1", String.class));
    }

    @Test
    public void testNotExistCacheObject() {
        given(cacheManager.getCache(anyString())).willReturn(cache);

        assertNull(cacheManagerImpl.get("testCache", "2", String.class));
    }

    @Test
    public void testPutObjectInCache() {
        given(cacheManager.getCache(anyString())).willReturn(cache);

        cacheManagerImpl.put("testCache", "2", "test");
    }

    @Test
    public void testPutObjectNullCache() {
        given(cacheManager.getCache(anyString())).willReturn(null);

        cacheManagerImpl.put("testCache", "2", "test");
    }

    @Test
    public void testPutNullObjectInCache() {
        given(cacheManager.getCache(anyString())).willReturn(null);

        cacheManagerImpl.put("testCache", "2", null);
    }

    @Test
    public void testPutNullKeyInCache() {
        given(cacheManager.getCache(anyString())).willReturn(null);

        cacheManagerImpl.put("testCache", null, "test");
    }
}
