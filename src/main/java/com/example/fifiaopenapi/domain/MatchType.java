package com.example.fifiaopenapi.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class MatchType {

    @Id
    private Long matchtype;
    private String desc;


    @Builder
    public MatchType(Long matchtype, String desc) {
        this.matchtype = matchtype;
        this.desc = desc;
    }
}
