package com.kogpt.demo.api;

import com.kogpt.demo.model.Header;
import com.kogpt.demo.model.request.KoGptApiRequestDto;
import com.kogpt.demo.model.response.KoGptApiResponseDto;
import com.kogpt.demo.service.KoGptServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "KoGpt", description = "kogpt 테스트")
@Slf4j
@RestController
@RequestMapping("/api/kgpt")
@RequiredArgsConstructor
public class KoGptApiController {

    private final KoGptServiceImpl koGptService;

    //    @ApiOperation(value = "KoGpt-1" ,notes = "KoGpt-2", tags = {"KoGpt"})
    @PostMapping("/request")
//    public Header<KoGptApiResponseDto> makeSentence(@RequestBody @Validated KoGptApiRequestDto koGptApiRequestDto) {
    public Header<KoGptApiResponseDto> makeSentence() {

        KoGptApiRequestDto koGptApiRequestDto = KoGptApiRequestDto.builder()
                .prompt("내가 오늘 프로그래머스 공부해볼려고 다른사람 풀이 봤는데 무슨소린지")
                .max_tokens(60)
                .temperature(1.0)
                .top_p(1.0)
                .n(1)
                .build();
        return Header.OK(koGptService.requestKoGpt(koGptApiRequestDto));
    }

}