package com.kogpt.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KoGptApiRequestDto {
    private String prompt;
    private Integer max_tokens;
    private Double temperature;
    private Double top_p;
    private Integer n;
}
