package com.tenpo.challenge.service.impl;

import com.tenpo.challenge.controller.HistoryController;
import com.tenpo.challenge.dto.PercentageDto;
import com.tenpo.challenge.dto.ResultDto;
import com.tenpo.challenge.dto.SumDto;
import com.tenpo.challenge.repository.PercentageApiRepository;
import com.tenpo.challenge.repository.cache.CacheStore;
import com.tenpo.challenge.service.LogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MathServiceImplTest {

    @Mock
    private PercentageApiRepository percentageApiRepository;
    @Mock
    private CacheStore<PercentageDto> percentageCache;

    @InjectMocks
    private MathServiceImpl mathServiceImpl;

    @BeforeEach
    public void setUp() {
        this.percentageCache = new CacheStore<>(60, TimeUnit.MINUTES);
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testSum() throws Exception {
        when(percentageCache.get(MathServiceImpl.cacheKey)).thenReturn(null);

        PercentageDto mockPercentageDto = new PercentageDto(10f, new Date());
        when(percentageApiRepository.Get()).thenReturn(Optional.of(mockPercentageDto));

        SumDto sumDto = new SumDto(50f, 50f);

        ResultDto resultDto = mathServiceImpl.sum(sumDto);

        assertNotNull(resultDto);
        assertEquals(110.0f, resultDto.getResult());
        assertEquals(10.0f, resultDto.getPercentage());
    }

    @Test
    public void testGetPercentageFromCache() throws Exception {
        PercentageDto cachedPercentageDto = new PercentageDto(10f, new Date());
        when(percentageCache.get(any())).thenReturn(cachedPercentageDto);

        SumDto sumDto = new SumDto(50f, 50f);

        ResultDto resultDto = mathServiceImpl.sum(sumDto);

        assertNotNull(resultDto);
        assertEquals(110.0f, resultDto.getResult());
        assertEquals(10.0f, resultDto.getPercentage());

    }

    @Test
    public void testGetPercentageWithRetry() throws Exception {
        when(percentageCache.get(MathServiceImpl.cacheKey)).thenReturn(null);

        PercentageDto mockPercentageDto = new PercentageDto(10f, new Date());
        when(percentageApiRepository.Get()).thenThrow(new Exception("API Error")).thenReturn(Optional.of(mockPercentageDto));
        SumDto sumDto = new SumDto(50f, 50f);

        ResultDto resultDto = mathServiceImpl.sum(sumDto);


        verify(percentageApiRepository, times(2)).Get();

        assertEquals(10.0f, resultDto.getPercentage());
    }

    @Test
    public void testGetPercentageApiErrorAndGetFromCache() throws Exception {
        var date = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(MathServiceImpl.timeCache + 10)  );
        PercentageDto mockPercentageDto = new PercentageDto(10f, date);
        when(percentageCache.get(MathServiceImpl.cacheKey)).thenReturn(mockPercentageDto);
        when(percentageApiRepository.Get()).thenThrow(new Exception("test ex"));

        SumDto sumDto = new SumDto(50f, 50f);

        ResultDto resultDto = mathServiceImpl.sum(sumDto);

        verify(percentageApiRepository, times(MathServiceImpl.retries)).Get();
        verify(percentageCache, times(1)).get(MathServiceImpl.cacheKey);
        assertEquals(10.0f, resultDto.getPercentage());
    }

}
