package com.kogpt.demo.api;

import com.kogpt.demo.model.request.KoGptApiRequestDto;
import com.kogpt.demo.service.KoGptServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "데모 테스트")
@Slf4j
@RestController
@RequestMapping("/api/kgpt")
@RequiredArgsConstructor
public class KoGptApiController {

    private final KoGptServiceImpl koGptService;

    @PostMapping("/request")
    public ResponseEntity makeSentence(
            @ApiParam(
                    value = "카카오 디벨로퍼에서 생성한 REST API 키",
                    required = true,
                    example = "167954313867aaaa7aa3a9750aaa"
            )
            @RequestParam String restApiKey,
            @RequestBody KoGptApiRequestDto json)
    {
        KoGptApiRequestDto koGptApiRequestDto = KoGptApiRequestDto.builder()
                .prompt(json.getPrompt())
                .max_tokens(json.getMax_tokens())
                .temperature(json.getTemperature())
                .top_p(json.getTop_p())
                .n(json.getN())
                .build();

        return ResponseEntity.ok().body(koGptService.requestKoGpt(koGptApiRequestDto, restApiKey));
    }

}