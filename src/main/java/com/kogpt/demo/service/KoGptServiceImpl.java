package com.kogpt.demo.service;

import com.kogpt.demo.common.ApiRestTemplateComponent;
import com.kogpt.demo.common.ObjectMapperUtil;
import com.kogpt.demo.model.Header;
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

    private final ApiRestTemplateComponent apiRestTemplateComponent;

    @Override
    public Header requestKoGpt(KoGptApiRequestDto koGptApiRequestDto, String restApiKey) {

        String url = host+endPoint;

        HttpHeaders headers = this.getHttpHeaders(restApiKey);
        String jsonParam = ObjectMapperUtil.writeValueAsString(koGptApiRequestDto);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonParam, headers);

        try {
            Future<ResponseEntity<String>> responseEntityFuture = apiRestTemplateComponent.postForEntity(url, httpEntity, String.class, koGptApiRequestDto);
            ResponseEntity<String> responseEntity = responseEntityFuture.get();

            KoGptApiResponseDto baseDto = ObjectMapperUtil.koGptReadValue(responseEntity.getBody(), KoGptApiResponseDto.class);
            return Header.OK(baseDto);
        }
        catch (Exception e) {
            return Header.ERROR(e.getMessage());
        }
    }



    @Override
    public HttpHeaders getHttpHeaders(String restApiKey) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            headers.set("Authorization", "KakaoAK "+restApiKey);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return headers;
    }
}
