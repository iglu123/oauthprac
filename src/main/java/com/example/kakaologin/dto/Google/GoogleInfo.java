package com.example.kakaologin.dto.Google;

import com.example.kakaologin.dto.Kakao.KakaoAccount;
import com.example.kakaologin.dto.Kakao.KakaoInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleInfo {
    private GoogleAccount googleAccount;

    public static GoogleInfo fail() {
        return null;
    }
}
