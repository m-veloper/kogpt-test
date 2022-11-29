package com.kogpt.demo.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class KoGptApiRequestDto {
    @ApiModelProperty(
            value = "KoGPT에게 전달할 제시어인 프롬프트 : 한국어만 지원",
            required = true,
            dataType = "String",
            example = "상품 후기를 긍정 또는 부정으로 분류합니다.\n"+
            "가격대비좀 부족한게많은듯=부정\n"+
            "재구매 친구들이 좋은 향 난다고 해요=긍정\n"+
            "ㅠㅠ약간 후회가 됩니다..=부정\n"+
            "이전에 먹고 만족해서 재구매합니다=긍정\n"+
            "튼튼하고 잘 쓸수 있을 것 같습니다. 이 가격에 이 퀄리티면 훌륭하죠="
    )
    private String prompt;

    @ApiModelProperty(
            value = "KoGPT가 생성할 결과의 최대 토큰 수 KoGPT는 지정된 최대 토큰 수 이하 길이의 결과만 반환\n" +
            "결과 토큰 수가 최대 토큰 수보다 적으면, 결과 생성 종료를 알리는 [EOS]까지의 결과만 반환",
            required = true,
            dataType = "String",
            example = "60"
    )
    private Integer max_tokens;

    @ApiModelProperty(
            value = "온도 설정 0 초과 1 이하의 실수 값 사용 가능 temperature 수치가 높을수록 더 창의적인 결과가 생성됨 (기본값: 1)",
            dataType = "Double",
            example = "1.0"
    )
    private Double temperature;

    @ApiModelProperty(
            value = "상위 확률 설정 0 이상 1 이하의 실수 값 사용 가능 top_p 수치가 높을수록 더 창의적인 결과가 생성됨 (기본값: 1)",
            dataType = "Double",
            example = "1.0"
    )
    private Double top_p;

    @ApiModelProperty(
            value = "KoGPT가 생성할 결과 수 설정값 만큼 요청을 처리하고 쿼터를 차감함 (최대: 16, 기본값: 1)",
            dataType = "Integer",
            example = "1"
    )
    private Integer n;

    public KoGptApiRequestDto(String prompt, Integer max_tokens, Double temperature, Double top_p, Integer n) {
        this.prompt = prompt;
        this.max_tokens = max_tokens;
        this.temperature = (temperature == 0 ? 1.0 : temperature);
        this.top_p = (top_p == 0 ? 1.0 : top_p);;
        this.n = (n == 0 ? 1 : n);;
    }
}
