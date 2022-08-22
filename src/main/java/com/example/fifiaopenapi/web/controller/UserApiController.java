package com.example.fifiaopenapi.web.controller;


import com.example.fifiaopenapi.service.UserService;
import com.example.fifiaopenapi.web.dto.MatchTypeDto;
import com.example.fifiaopenapi.web.dto.PlayerMetadataInfoDto;
import com.example.fifiaopenapi.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/api/players")
//    public PlayerMetadataInfoDto [] savePlayerInfo() {
//        return userService.savePlayerMetadataInfo();
//    }

//    @GetMapping("/api/matchTypes")
//    public MatchTypeDto[] getMatchTypes() {
//        return userService.getMatchType();
//    }
}
