package com.tenpo.challenge.repository.cache;

import com.tenpo.challenge.dto.PercentageDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreBeans {

    @Bean
    public CacheStore<PercentageDto> percentageApiCache() {
        return new CacheStore<PercentageDto>(60, TimeUnit.MINUTES);
    }

}
