package com.example.fifiaopenapi.web.dto;


import com.example.fifiaopenapi.domain.MatchType;
import lombok.Data;

@Data
public class MatchTypeDto {
    private Long matchtype;
    private String desc;

    public MatchType toEntity() {
        return MatchType.builder()
                .matchtype(this.matchtype)
                .desc(this.desc)
                .build();
    }
}
