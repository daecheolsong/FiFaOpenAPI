package com.example.fifiaopenapi.web.dto;

import lombok.Data;

@Data
public class MatchDetailDto {
    private Integer seasonId;
    private String matchResult;
    private Integer matchEndType;
    private Integer systemPause;
    private Integer foul;
    private Integer injury;
    private Integer redCards;
    private Integer yellowCards;
    private Integer dribble;
    private Integer cornerKick;
    private Integer possession;
    private Integer OffsideCount;
    private Double averageRating;
    private String controller;
}
