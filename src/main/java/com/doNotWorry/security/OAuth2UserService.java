package com.doNotWorry.security;


import com.doNotWorry.account.AccountService;
import com.doNotWorry.user.SiteUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final AccountService accountService;

    // 카카오톡 로그인이 성공할 때 마다 이 함수가 실행된다.
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String oauthId = oAuth2User.getName();

        String oauthType = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        if (!"KAKAO".equals(oauthType)) {
            throw new RuntimeException("카카오로그인만 가능합니다.");
        }

        String nickname = null;
        String email = null;
        String loginID = null;
        String profileImgUrl = null;

        switch (oauthType) {
            case "KAKAO" -> {
                Map attributesProperties = (Map) attributes.get("properties");
                nickname = (String) attributesProperties.get("nickname");
                profileImgUrl = (String) attributesProperties.get("profile_image");
                email = "%s@kakao.com".formatted(oauthId);
                loginID = "KAKAO_%s".formatted(oauthId);
            }
        }

        log.debug("oauthId: {}", oauthId);
        log.debug("oauthType: {}", oauthType);
        log.debug("nickname: {}", nickname);
        log.debug("email: {}", email);
        log.debug("loginID: {}", loginID);
        log.debug("profileImgUrl: {}", profileImgUrl);

        // TODO: 회원가입, 로그인 처리
        SiteUser siteUser = accountService.whenSocialLogin(oauthType, loginID, email, nickname, profileImgUrl);

        return User.from(siteUser);
    }
}