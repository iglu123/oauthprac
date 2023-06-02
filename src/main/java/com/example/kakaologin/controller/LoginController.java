package com.example.kakaologin.controller;

import com.example.kakaologin.dto.Naver.NaverAccount;
import com.example.kakaologin.service.*;
import feign.Headers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.kakaologin.dto.Kakao.KakaoAccount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final KakaoService kakaoService;
    private final NaverService naverService;
    private final GoogleService googleService;
    private final GithubService githubService;
    private final FacebookService facebookService;


    @RequestMapping(value="/login",produces = "application/json")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
    /**
     * https://kauth.kakao.com/oauth/authorize?response_type=code&client_id={여기에 REST API KEY를 입력해주세요}&redirect_uri={여기에 지정한 RedirectURL을 입력해주세요}
     * 위의 결과로 code를 받아와서, 해당 코드를 통해 카카오 인증 서버에서 accessToken/refreshToken을 받아옵니다.
     */
    @GetMapping("/callback/login")
    public KakaoAccount getKakaoAccount(@RequestParam("code") String code) {
        log.debug("code = {}", code);
        return kakaoService.getInfo(code).getKakaoAccount();
    }

    @GetMapping("/callback/login/naver")
    public ResponseEntity<String> getNaverAccount(@RequestParam("code") String code) {
        log.debug("code = {}", code);
        return naverService.getInfo(code);
    }

    @GetMapping(value="/callback/login/google")
    public ResponseEntity<String> getGoogleAccount(@RequestParam("code") String code) {
        log.debug("code = {}", code);
        return googleService.getInfo(code);
    }

    @GetMapping("/callback/login/github")
    public ResponseEntity<String> getGithubAccount(@RequestParam("code") String code) {
        log.debug("code = {}", code);
        return githubService.getInfo(code);
    }

    @GetMapping("/callback/login/facebook")
    public ResponseEntity<String> getFacebookAccount(@RequestParam("code") String code) {

        log.debug("code = {}", code);
        return facebookService.getInfo(code);
    }



//    @GetMapping("/callback/logout")
//    public KakaoAccount getKakaoAccount(@RequestParam("code") String code) {
//        log.debug("code = {}", code);
//        return kakaoService.getInfo(code).getKakaoAccount();
//    }
}
