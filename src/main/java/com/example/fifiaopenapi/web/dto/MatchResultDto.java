package com.example.fifiaopenapi.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchResultDto {
    String matchId;
    String matchDate;
    String matchType;
    List<MatchInfoDto> matchInfo;
}



