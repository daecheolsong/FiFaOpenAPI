package com.example.fifiaopenapi.web.dto;

import lombok.Data;

@Data
public class StatusDto {
    private Integer shoot;
    private Integer effectiveShoot;
    private Integer assist;
    private Integer goal;
    private Integer dribble;
    private Integer intercept;
    private Integer defending;
    private Integer passTry;
    private Integer passSuccess;
    private Integer dribbleTry;
    private Integer dribbleSuccess;
    private Integer ballPossesionTry;
    private Integer ballPossesionSuccess;
    private Integer aerialTry;
    private Integer aerialSuccess;
    private Integer blockTry;
    private Integer block;
    private Integer tackleTry;
    private Integer tackle;
    private Integer yellowCards;
    private Integer redCards;
    private Float spRating;
}
