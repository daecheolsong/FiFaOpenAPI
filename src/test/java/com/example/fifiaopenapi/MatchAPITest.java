package com.example.fifiaopenapi;

import com.example.fifiaopenapi.service.UserService;
import com.example.fifiaopenapi.web.dto.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MatchAPITest {

    @Autowired
    UserService userService;

    @Test
    public void 매치결과API() {
        //given
        UserInfoDto 호날두 = userService.getUserInfo("호날두");
        String[] userMatchIds = userService.getUserMatchIds(호날두.getAccessId(), "50", "0", "1");

        //when
        MatchResultDto matchResultDto = userService.getMatchResultByMatchId(userMatchIds[0]);
        //then
        Assertions.assertThat(matchResultDto).isNotNull(); // matchResultDto

        List<MatchInfoDto> matchInfo = matchResultDto.getMatchInfo();
        Assertions.assertThat(matchInfo.size()).isEqualTo(2); // matchInfo Dto
        for (MatchInfoDto matchInfoDto : matchInfo) {
            List<ShootDetailDto> shootDetail = matchInfoDto.getShootDetail(); // shootDetailDto
            Assertions.assertThat(shootDetail).isNotNull();

            List<PlayerDto> player = matchInfoDto.getPlayer(); //playerDto
            Assertions.assertThat(player.size()).isEqualTo(18);
        }
    }

}
