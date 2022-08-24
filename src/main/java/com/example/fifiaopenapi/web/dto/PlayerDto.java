package com.example.fifiaopenapi.web.dto;

import lombok.Data;

@Data
public class PlayerDto {
    private Integer spId;
    private Integer spPosition;
    private Integer spGrade;
    private StatusDto status;
}
