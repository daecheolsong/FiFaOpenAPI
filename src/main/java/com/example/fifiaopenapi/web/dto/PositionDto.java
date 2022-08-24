package com.example.fifiaopenapi.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PositionDto {
    @JsonProperty("spposition")
    private Long spPosition;
    private String desc;
}
