package com.example.kakaologin.dto.Naver;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NaverAccount {
    private String resultcode;
    private String message;
    private UserInfo response;

    // Getters and setters

    public static class UserInfo {
        private String id;
        private String profile_image;
        private String gender;
        private String name;
        private String mobile;
        private String mobile_el64;

        // Getters and setters
    }
}
