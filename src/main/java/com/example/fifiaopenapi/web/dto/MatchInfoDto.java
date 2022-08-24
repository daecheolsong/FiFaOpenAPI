package com.example.fifiaopenapi.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchInfoDto {
    private String accessId;
    private String nickname;
    private MatchDetailDto matchDetail;
    private ShootDto shoot;
    private List<ShootDetailDto> shootDetail;
    private PassDto pass;
    private DefenceDto defence;
    private List<PlayerDto> player;
}