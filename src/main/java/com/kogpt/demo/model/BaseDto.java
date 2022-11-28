package com.kogpt.demo.model;

import com.kogpt.demo.common.ObjectMapperUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto<T> {

    private Map<String, Object> status;
    private T result;

    public String toString() {
        return ObjectMapperUtil.writeValueAsString(this);
    }

}
