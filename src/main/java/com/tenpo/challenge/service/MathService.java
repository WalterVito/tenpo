package com.tenpo.challenge.service;

import com.tenpo.challenge.dto.ResultDto;
import com.tenpo.challenge.dto.SumDto;

public interface MathService {


    ResultDto sum(SumDto sumDto) throws Exception;
}
