package com.example.fifiaopenapi.service;

import com.example.fifiaopenapi.api.ApiClient;
import com.example.fifiaopenapi.domain.Player;
import com.example.fifiaopenapi.domain.SeasonId;
import com.example.fifiaopenapi.repository.MatchTypeRepository;
import com.example.fifiaopenapi.repository.PlayerRepository;
import com.example.fifiaopenapi.repository.SeasonIdRepository;
import com.example.fifiaopenapi.web.dto.MatchTypeDto;
import com.example.fifiaopenapi.web.dto.PlayerMetadataInfoDto;
import com.example.fifiaopenapi.web.dto.SeasonIdDto;
import com.example.fifiaopenapi.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserService {
    private final ApiClient apiClient;
    private final PlayerRepository playerRepository;
    private final MatchTypeRepository matchTypeRepository;
    private final SeasonIdRepository seasonIdRepository;

    private final String actionImageUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p{spid}.png";
    private final String commonImageUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p{spid}.png";

    public UserInfoDto getUserInfo(String nickname) {
        return apiClient.requestUserInfo(nickname);
    }


    // save meta data

    public void savePlayerMetadataInfo() {
        PlayerMetadataInfoDto[] playerMetadataInfoDtos = apiClient.requestPlayerMetadataInfo();
        ArrayList<Player> savePlayers = new ArrayList<>();
        for (int i = 0; i < playerMetadataInfoDtos.length; i++) {
            playerMetadataInfoDtos[i].setActionImage(actionImageUrl.replace("{spid}",String.valueOf(playerMetadataInfoDtos[i].getId())));
            playerMetadataInfoDtos[i].setCommonImage(commonImageUrl.replace("{spid}",String.valueOf(playerMetadataInfoDtos[i].getId())));
            savePlayers.add(playerMetadataInfoDtos[i].toEntity());
        }

        playerRepository.saveAll(savePlayers);
    }
//    public MatchTypeDto[] getMatchType() {
//        MatchTypeDto[] matchTypeDtos = apiClient.requestMatchType();
//        return matchTypeDtos;
//    }



    public void saveMatchType() {
        MatchTypeDto[] matchTypes = apiClient.requestMatchType();
        for (MatchTypeDto matchType : matchTypes) {
            matchTypeRepository.save(matchType.toEntity());
        }
    }

    public void saveSeasonId() {
        SeasonIdDto[] seasonIdDtos = apiClient.requestSeasonId();
        ArrayList<SeasonId> seasonIds = new ArrayList<>();
        for (int i = 0; i < seasonIdDtos.length; i++) {
            seasonIds.add(seasonIdDtos[i].toEntity());
        }
        seasonIdRepository.saveAll(seasonIds);

    }

    public void setMetaData() {
        StopWatch stopWatch = new StopWatch("save Metadata");
        stopWatch.start("save MatchType");
        saveMatchType();
        stopWatch.stop();
        stopWatch.start("save PlayerMetaData");
        savePlayerMetadataInfo();
        stopWatch.stop();
        stopWatch.start("save seasonId");
        saveSeasonId();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
