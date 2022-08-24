package com.example.fifiaopenapi.web.dto;

import lombok.Data;

@Data
public class ShootDto {
    private Integer shootTotal;
    private Integer effectiveShootTotal;
    private Integer shootOutScore;
    private Integer goalTotal;
    private Integer goalTotalDisplay;
    private Integer ownGoal;
    private Integer shootHeading;
    private Integer goalHeading;
    private Integer shootFreekick;
    private Integer goalFreekick;
    private Integer shootInPenalty;
    private Integer goalInPenalty;
    private Integer shootOutPenalty;
    private Integer goalOutPenalty;
    private Integer shootPenaltyKick;
    private Integer goalPenaltyKick;

}
