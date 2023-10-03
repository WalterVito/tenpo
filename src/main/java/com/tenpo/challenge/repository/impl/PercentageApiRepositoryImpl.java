package com.tenpo.challenge.repository.impl;

import com.tenpo.challenge.dto.PercentageDto;
import com.tenpo.challenge.repository.PercentageApiRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Repository
public class PercentageApiRepositoryImpl implements PercentageApiRepository {

    @Override
    public Optional<PercentageDto> Get() {
        return Optional.empty();
    }
}
