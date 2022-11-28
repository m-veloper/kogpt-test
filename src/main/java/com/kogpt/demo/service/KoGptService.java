package com.kogpt.demo.service;

import com.kogpt.demo.model.request.KoGptApiRequestDto;
import org.springframework.http.HttpHeaders;

import javax.validation.constraints.NotNull;

public interface KoGptService {

    /**
     * KoGPY API 요청
     *
     * @param koGptApiRequestDto
     * @return
     */
    Object requestKoGpt(@NotNull KoGptApiRequestDto koGptApiRequestDto);

    HttpHeaders getHttpHeaders();
}

