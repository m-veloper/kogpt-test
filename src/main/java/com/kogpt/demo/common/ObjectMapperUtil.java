package com.kogpt.demo.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kogpt.demo.model.BaseDto;
import com.kogpt.demo.model.response.KoGptApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ObjectMapperUtil<T> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * object to jsonString
     * @param object
     * @return
     */
    public static String writeValueAsString(@NotNull Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * object to Map
     * @param object
     * @return
     */
    public static MultiValueMap<String, String> convertValue(@NotNull Object object) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            Map<String, String> map = objectMapper.convertValue(object, new TypeReference<Map<String, String>>() {});
            params.setAll(map);
            return params;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param json
     * @param t
     * @return
     * @param <T>
     * @throws IOException
     */
    public static <T> T koGptReadValue(@NotNull String json, Class<T> t) throws IOException {
        T target = objectMapper.readValue(json, t);;
        return target;
    }

    /**
     * map to string
     * @param map
     * @return
     */
    public static String mapToString(Map<?, ?> map) {
        try {
            return objectMapper.writeValueAsString(map);
        }
        catch (JsonProcessingException e) {
            return null;
        }
    }

}