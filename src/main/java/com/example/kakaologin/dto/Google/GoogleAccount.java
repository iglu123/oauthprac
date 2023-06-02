package com.example.kakaologin.dto.Google;

import com.example.kakaologin.dto.Kakao.Profile;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GoogleAccount {

        private Profile profile;
        private String name;
        private String birthday;
        private String email;
    }

