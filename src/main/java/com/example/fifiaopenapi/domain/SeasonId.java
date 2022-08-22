package com.example.fifiaopenapi.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class SeasonId {

    @Id
    private Long seasonId;
    private String className;
    private String seasonImg;

    @Builder
    public SeasonId(Long seasonId, String className, String seasonImg) {
        this.seasonId = seasonId;
        this.className = className;
        this.seasonImg = seasonImg;
    }
}
