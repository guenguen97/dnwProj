package com.doNotWorry.kakao;

import com.doNotWorry.common.MsgEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("kakao")
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/login")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
        System.out.println(kakaoInfo.getId());
        System.out.println(kakaoInfo.getEmail());
        System.out.println(kakaoInfo.getNickname());
        return ResponseEntity.ok()
                .body(new MsgEntity("Success", kakaoInfo));
    }
}