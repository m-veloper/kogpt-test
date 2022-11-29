package com.kogpt.demo.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class KoGptApiResponseDto {

    private String id;
    private List<Generation> generations;
    private Usage usage;

    @Data
    static class Generation{
        private String text;                // KoGPT가 생성한 결과
        private Integer tokens;             // 결과 토큰 수
    }

    @Data
    static class Usage{
        @JsonProperty("prompt_tokens")
        private Integer promptTokens;       // KoGPT에게 전달된 프롬프트의 토큰 수
        @JsonProperty("generated_tokens")
        private Integer generatedTokens;    // KoGPT가 생성한 결과의 토큰 수 tokens x n
        @JsonProperty("total_tokens")
        private Integer totalTokens;        // 	총 토큰 수 prompt_tokens x n + generated_tokens
    }
}