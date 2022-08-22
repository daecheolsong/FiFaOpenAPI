package com.example.fifiaopenapi.api;

import com.example.fifiaopenapi.web.dto.MatchTypeDto;
import com.example.fifiaopenapi.web.dto.PlayerMetadataInfoDto;
import com.example.fifiaopenapi.web.dto.SeasonIdDto;
import com.example.fifiaopenapi.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ApiClient {

    private final RestTemplate restTemplate;
    private final String API_KEY = Apikey.API_KEY;
    private final String UserInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
    private final String playerMetadataInfoUrl = "https://static.api.nexon.co.kr/fifaonline4/latest/spid.json";
    private final String matchTypeUrl = "https://static.api.nexon.co.kr/fifaonline4/latest/matchtype.json";
    private final String seasonIdUrl = "https://static.api.nexon.co.kr/fifaonline4/latest/seasonid.json";
    private final String actionImageUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p{spid}.png";
    private final String commonImageUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p{spid}.png";
    public UserInfoDto requestUserInfo(String nickname) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", API_KEY);
        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);

        // String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables
        ResponseEntity<UserInfoDto> response = restTemplate.exchange(UserInfoUrl, HttpMethod.GET, entity, UserInfoDto.class, nickname);
        return response.getBody();
    }


    public PlayerMetadataInfoDto[] requestPlayerMetadataInfo() {
        // String url, Class<T> responseType, Object... uriVariables
        ResponseEntity<PlayerMetadataInfoDto[]> playerMetadataInfoList = restTemplate.getForEntity(playerMetadataInfoUrl, PlayerMetadataInfoDto[].class);
        return playerMetadataInfoList.getBody();
    }

    public MatchTypeDto[] requestMatchType() {
        ResponseEntity<MatchTypeDto[]> matchTypeList = restTemplate.getForEntity(matchTypeUrl, MatchTypeDto[].class);
        return matchTypeList.getBody();
    }

    public SeasonIdDto[] requestSeasonId() {
        ResponseEntity<SeasonIdDto[]> seasonIdList = restTemplate.getForEntity(seasonIdUrl, SeasonIdDto[].class);
        return seasonIdList.getBody();

    }



}
