package com.tenpo.challenge.service.impl;

import com.tenpo.challenge.dto.PercentageDto;
import com.tenpo.challenge.dto.ResultDto;
import com.tenpo.challenge.dto.SumDto;
import com.tenpo.challenge.repository.PercentageApiRepository;
import com.tenpo.challenge.repository.cache.CacheStore;
import com.tenpo.challenge.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class MathServiceImpl implements MathService {
    static final String cacheKey = "cache-percentage";
    static final int timeCache = 30;
    static final int retries = 3;
    private final PercentageApiRepository percentageApiRepository;

    @Autowired
    CacheStore<PercentageDto> percentageCache;

    @Autowired
    public MathServiceImpl(
            PercentageApiRepository percentageApiRepository) {
        this.percentageApiRepository = percentageApiRepository;
    }


    @Override
    public ResultDto sum(SumDto sumDto) throws Exception {
        var percentage = getPercetage();
        return new ResultDto((sumDto.getNum1() + sumDto.getNum2()) *  (1 + percentage/100), percentage);
    }

    private float getPercetage() throws Exception{
        //Check if available in cache and time is valid and return current cache
        PercentageDto percentageCh = percentageCache.get(cacheKey);
        var dateExpired = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(timeCache));
        if (percentageCh != null && percentageCh.getTime().compareTo(dateExpired) < 0){
            return percentageCh.getPercentage();
        }
        //get from repository with retries (this logic maybe we can put in the repository but is a simple mock)
        Exception lastEx = null;
        for (var i = 0 ;i < retries;i++) {
            try {
                //get and put in cache
                PercentageDto percentage = percentageApiRepository.Get().orElse(null);
                if (percentage != null) {
                    percentage.setTime(new Date());
                    percentageCache.add(cacheKey,percentage);
                    return percentage.getPercentage();
                }
            } catch (Exception ex) {
                lastEx = ex;
            }
        }
        if (lastEx != null && percentageCh != null)
        {
            return percentageCh.getPercentage();
        }
        throw lastEx;
    }
}
