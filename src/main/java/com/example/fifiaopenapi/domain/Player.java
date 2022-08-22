package com.example.fifiaopenapi.domain;


import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Player {

    @Id
    private Long sid;
    private String name;
    private String actionImage;
    private String commonImage;




    @Builder
    public Player(Long sid, String name, String actionImage, String commonImage) {
        this.sid = sid;
        this.name = name;
        this.actionImage = actionImage;
        this.commonImage = commonImage;
    }
}
