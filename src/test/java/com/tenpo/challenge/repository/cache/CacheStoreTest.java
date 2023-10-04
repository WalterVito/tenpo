package com.tenpo.challenge.repository.cache;

import com.tenpo.challenge.dto.PercentageDto;
import com.tenpo.challenge.exception.CustomExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CacheStoreTest {

    private CacheStore<PercentageDto> cacheStore;
    @BeforeEach
    void setUp() {
        cacheStore = new CacheStore<>(60, TimeUnit.MINUTES);
    }
    @Test
    void putAndGetKey() {
        final String cacheKey = "test-key";
        final String notFoundCacheKey = "test-key-non-existent";
        PercentageDto percentageDto= new PercentageDto(10f,new Date());
        cacheStore.add(cacheKey,percentageDto);
        var cachePercentage = cacheStore.get(cacheKey);
        assertEquals(cachePercentage.getPercentage(),percentageDto.getPercentage());
        assertEquals(cachePercentage.getTime(),percentageDto.getTime());
        var notFoundCachePercentage = cacheStore.get(notFoundCacheKey);
        assertNull(notFoundCachePercentage);
    }
}