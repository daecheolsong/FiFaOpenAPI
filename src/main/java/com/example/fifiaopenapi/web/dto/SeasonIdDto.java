package com.example.fifiaopenapi.web.dto;

import com.example.fifiaopenapi.domain.SeasonId;
import lombok.Data;

@Data
public class SeasonIdDto {

    private Long seasonId;
    private String className;
    private String seasonImg;

    public SeasonId toEntity() {

        return SeasonId.builder()
                .seasonId(this.seasonId)
                .className(this.className)
                .seasonImg(this.seasonImg)
                .build();
    }

}
