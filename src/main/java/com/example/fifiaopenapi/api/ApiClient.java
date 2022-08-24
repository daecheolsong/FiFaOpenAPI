package com.example.fifiaopenapi.api;

import com.example.fifiaopenapi.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApiClient {

    private final WebClient webClient;
//    private final RestTemplate restTemplate; Deprecated
    private final String API_KEY = Apikey.API_KEY;
    private final String USER_URL = "https://api.nexon.co.kr/fifaonline4/v1.0/users";
    private final String MATCH_URL = "https://api.nexon.co.kr/fifaonline4/v1.0/matches";

    public UserInfoDto requestUserInfo(String nickname) {

        return WebClient.create(USER_URL)
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .queryParam("nickname", nickname)
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

    public List<PositionDto> requestWebClientPosition() {

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/fifaonline4/latest/spposition.json")
                        .build())
                .retrieve()
                .bodyToFlux(PositionDto.class)
                .toStream()
                .collect(Collectors.toList());
    }

    public List<DivisionDto> requestWebClientDivision() {

        return
                webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder.path("/fifaonline4/latest/division.json")
                                .build())
                        .retrieve()
                        .bodyToFlux(DivisionDto.class)
                        .toStream()
                        .collect(Collectors.toList());
    }

    // 유저 매치 ID
    public String[] requestWebClientUserMatchIds(String accessId, String matchType, String offset, String limit) {

        return
                WebClient
                        .create(USER_URL)
                        .get()
                        .uri(uriBuilder -> uriBuilder.path("/{accessid}/matches")
                                .queryParam("matchType", matchType)
                                .queryParam("offset", offset)
                                .queryParam("limit", limit)
                                .build(accessId))
                        .header(HttpHeaders.AUTHORIZATION, API_KEY)
                        .retrieve()
                        .bodyToMono(String[].class)
                        .block();

    }

    public MatchResultDto requestWebClientUserResultByMatchId(String matchId) {
        return WebClient.create(MATCH_URL)
                .get()
                .uri(uriBuilder -> uriBuilder.path("/{matchid}")
                        .build(matchId))
                .header(HttpHeaders.AUTHORIZATION, API_KEY)
                .retrieve()
                .bodyToMono(MatchResultDto.class)
                .block();
    }

}
