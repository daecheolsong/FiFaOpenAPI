package com.example.fifiaopenapi.web.dto;

import lombok.Data;

@Data
public class PassDto {
    private Integer passTry;
    private Integer passSuccess;
    private Integer shortPassTry;
    private Integer shortPassSuccess;
    private Integer longPassTry;
    private Integer longPassSuccess;
    private Integer bouncingLobPassTry;
    private Integer bouncingLobPassSuccess;
    private Integer drivenGroundPassTry;
    private Integer drivenGroundPassSuccess;
    private Integer throughPassTry;
    private Integer throughPassSuccess;
    private Integer lobbedThroughPassTry;
    private Integer lobbedThroughPassSuccess;
}
