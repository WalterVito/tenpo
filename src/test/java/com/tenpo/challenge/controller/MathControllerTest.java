package com.tenpo.challenge.controller;

import com.tenpo.challenge.dto.ResultDto;
import com.tenpo.challenge.dto.SumDto;
import com.tenpo.challenge.model.Log;
import com.tenpo.challenge.service.LogService;
import com.tenpo.challenge.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MathControllerTest {
    @Mock
    private MathService mathService;

    @InjectMocks
    private MathController mathController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sum() throws Exception {

        SumDto sumDto = new SumDto(50f,50f);
        ResultDto  resultDto = new ResultDto(100f,10f);
        when(mathService.sum(any())).thenReturn(resultDto);


        ResponseEntity<ResultDto> response = mathController.sum(sumDto);

        verify(mathService, times(1)).sum(any(SumDto.class));
        assertEquals(HttpStatus.OK, ((ResponseEntity<?>) response).getStatusCode());
        var result = response.getBody();
        assertEquals(resultDto.getResult(),result.getResult());
        assertEquals(resultDto.getPercentage(),result.getPercentage());
    }

}