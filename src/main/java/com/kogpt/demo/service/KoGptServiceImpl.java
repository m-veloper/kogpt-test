package com.kogpt.demo.service;

import com.kogpt.demo.common.ApiRestTemplateComponent;
import com.kogpt.demo.common.ObjectMapperUtil;
import com.kogpt.demo.model.BaseDto;
import com.kogpt.demo.model.request.KoGptApiRequestDto;
import com.kogpt.demo.model.response.KoGptApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@RequiredArgsConstructor
@Service
public class KoGptServiceImpl implements KoGptService {

    @Value("${kogpt-api.host}")
    private String host;
    @Value("${kogpt-api.endpoint}")
    private String endPoint;
    @Value("${kogpt-api.rest-api-key}")
    private String key;
    private final ApiRestTemplateComponent apiRestTemplateComponent;

    @Override
    public KoGptApiResponseDto requestKoGpt(KoGptApiRequestDto koGptApiRequestDto) {

        String url = host+endPoint;

        HttpHeaders headers = this.getHttpHeaders();
        String jsonParam = ObjectMapperUtil.writeValueAsString(koGptApiRequestDto);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonParam, headers);

        try {
            Future<ResponseEntity<String>> responseEntityFuture = apiRestTemplateComponent.postForEntity(url, httpEntity, String.class, koGptApiRequestDto);
            ResponseEntity<String> responseEntity = responseEntityFuture.get();

            KoGptApiResponseDto baseDto = ObjectMapperUtil.koGptReadValue(responseEntity.getBody(), KoGptApiResponseDto.class);
            return baseDto;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            headers.set("Authorization", key);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return headers;
    }
}
