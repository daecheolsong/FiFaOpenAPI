package com.example.fifiaopenapi.web.dto;

import com.example.fifiaopenapi.domain.Player;
import lombok.Data;

@Data
public class PlayerMetadataInfoDto {

    private Long id;
    private String name;
    private String actionImage;
    private String commonImage;

    public Player toEntity() {
        return Player.builder()
                .sid(this.id)
                .name(this.name)
                .actionImage(actionImage)
                .commonImage(commonImage)
                .build();
    }

}
