package com.example.fifiaopenapi.api;

import com.example.fifiaopenapi.web.dto.MatchTypeDto;
import com.example.fifiaopenapi.web.dto.PlayerMetadataInfoDto;
import com.example.fifiaopenapi.web.dto.SeasonIdDto;
import com.example.fifiaopenapi.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApiClient {

    private final WebClient webClient;
//    private final RestTemplate restTemplate; Deprecated
    private final String API_KEY = Apikey.API_KEY;
    private final String UserInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users";

    public UserInfoDto requestUserInfo(String nickname) {

        return WebClient.create(UserInfoUrl)
                .get()
                .uri(uriBuilder ->
                        uriBuilder.queryParam("nickname", nickname)
                                .build())
                .header(HttpHeaders.AUTHORIZATION, API_KEY)
                .retrieve()
                .bodyToMono(UserInfoDto.class)
                .block();
    }

    // Deprecated
//    public PlayerMetadataInfoDto[] requestPlayerMetadataInfo() {
//        // String url, Class<T> responseType, Object... uriVariables
//        ResponseEntity<PlayerMetadataInfoDto[]> playerMetadataInfoList = restTemplate.getForEntity(playerMetadataInfoUrl, PlayerMetadataInfoDto[].class);
//        return playerMetadataInfoList.getBody();
//    }


    // Deprecated
//    public MatchTypeDto[] requestMatchType() {
//        ResponseEntity<MatchTypeDto[]> matchTypeList = restTemplate.getForEntity(matchTypeUrl, MatchTypeDto[].class);
//        return matchTypeList.getBody();
//    }

    // Deprecated
//    public SeasonIdDto[] requestSeasonId() {
//        ResponseEntity<SeasonIdDto[]> seasonIdList = restTemplate.getForEntity(seasonIdUrl, SeasonIdDto[].class);
//        return seasonIdList.getBody();
//
//    }
    public List<PlayerMetadataInfoDto> requestWebClientPlayerMetadataInfo() {

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/fifaonline4/latest/spid.json")
                        .build())
                .retrieve()
                .bodyToFlux(PlayerMetadataInfoDto.class)
                .toStream()
                .collect(Collectors.toList());

    }

    public List<MatchTypeDto> requestWebClientMatchType() {

        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/fifaonline4/latest/matchtype.json")
                                .build())
                .retrieve()
                .bodyToFlux(MatchTypeDto.class)
                .toStream()
                .collect(Collectors.toList());

    }

    public List<SeasonIdDto> requestWebClientSeasonId() {

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/fifaonline4/latest/seasonid.json")
                        .build())
                .retrieve()
                .bodyToFlux(SeasonIdDto.class)
                .toStream()
                .collect(Collectors.toList());
    }




}
