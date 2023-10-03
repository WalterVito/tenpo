package com.tenpo.challenge.service.impl;

import com.tenpo.challenge.dto.ResultDto;
import com.tenpo.challenge.dto.SumDto;
import com.tenpo.challenge.repository.PercentageApiRepository;
import com.tenpo.challenge.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MathServiceImpl implements MathService {
    private final PercentageApiRepository percentageApiRepository;
    @Autowired
    public MathServiceImpl(
            PercentageApiRepository percentageApiRepository) {
        this.percentageApiRepository = percentageApiRepository;
    }


    @Override
    public ResultDto sum(SumDto sumDto) {
        var percentage = percentageApiRepository.Get();
        return null;
    }
}
