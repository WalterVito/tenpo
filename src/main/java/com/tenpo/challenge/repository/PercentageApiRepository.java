package com.tenpo.challenge.repository;

import com.tenpo.challenge.dto.PercentageDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PercentageApiRepository  {
    Optional<PercentageDto> Get() throws Exception;
}
