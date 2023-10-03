package com.tenpo.challenge.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PercentageDto {
    private float percentage;
    private Date Time;

    public PercentageDto(float percentage) {
        this.percentage = percentage;
    }
}
