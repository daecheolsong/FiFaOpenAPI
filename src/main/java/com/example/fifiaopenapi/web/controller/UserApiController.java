package com.example.fifiaopenapi.web.controller;


import com.example.fifiaopenapi.service.UserService;
import com.example.fifiaopenapi.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/")
    public String init() {
        userService.setMetaData();
        return "saved successfully";
    }

    @GetMapping("/api/user/{nickname}")
    public UserInfoDto getUserInfo(@PathVariable String nickname) {
        return userService.getUserInfo(nickname);
    }

    @GetMapping("/api/position")
    public List<PositionDto> getPosition() {
        return userService.getPosition();
    }

    @GetMapping("/api/division")
    public List<DivisionDto> getDivision() {
        return userService.getDivision();
    }

    // 유저 ID 로 매치 ID 조회
    @GetMapping("/api/user/matches/{accessId}/{matchtype}")
    public String[] getUserMatchIds(@PathVariable String accessId,
                                       @PathVariable("matchtype") String matchType,
                                       @RequestParam(required = false, defaultValue = "0") String offset,
                                       @RequestParam(required = false, defaultValue = "20") String limit) {
        return userService.getUserMatchIds(accessId, matchType, offset, limit);
    }

    // 매치 ID 로 매치 결과 조회
    @GetMapping("/api/match/result/{matchid}")
    public MatchResultDto getMatchResult(@PathVariable("matchid") String matchId) {
        return userService.getMatchResultByMatchId(matchId);
    }

}
