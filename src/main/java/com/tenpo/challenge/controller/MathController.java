package com.tenpo.challenge.controller;
import com.tenpo.challenge.dto.ResultDto;
import com.tenpo.challenge.dto.SumDto;
import com.tenpo.challenge.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/math")
public class MathController {


    private final MathService mathService;

    @Autowired
    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @PostMapping("/sum")
    public ResultDto sum(@RequestBody SumDto sumDto) {
        return this.mathService.sum(sumDto);
    }

}
