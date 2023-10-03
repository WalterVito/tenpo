package com.tenpo.challenge.repository.impl;

import com.tenpo.challenge.dto.PercentageDto;
import com.tenpo.challenge.exception.RepositoryException;
import com.tenpo.challenge.repository.PercentageApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Random;

@Repository
public class PercentageApiRepositoryMock implements PercentageApiRepository {

    static final int min = 1;
    static final int maxRand = 110;
    static final int maxPercentage = 100;

    @Override
    public Optional<PercentageDto> Get() {
        //int forcedEx = 1/0;
        Random rand = new Random();
        var random = rand.nextInt(maxRand - min + 1) + min;
        if (random > maxPercentage)
        {
            throw new RepositoryException("ERROR API MOCKED EXCEED MAX PERCENTAGE EXPECTED");
        }

        return Optional.of(new PercentageDto(random));
    }
}
