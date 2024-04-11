package com.doNotWorry.account;


import com.doNotWorry.user.SiteUser;
import com.doNotWorry.user.UserRepository;
import com.doNotWorry.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    public SiteUser whenSocialLogin(String oauthType, String loginID, String email, String nickname, String profileImgUrl) {
        Optional<SiteUser> memberByUsername = userRepository.findByLoginID(loginID);

        if (memberByUsername.isPresent()) {
            SiteUser user = memberByUsername.get();

            if(user.getProfileImgUrl().equals("") && user.getNickName().equals("")) {
                user.updateWhenSocialLogin(nickname, profileImgUrl);
            }
            user.setCreateDate(LocalDateTime.now());
            return user;
        }

        return joinWithSocialLogin(oauthType, loginID, email, nickname, profileImgUrl);
    }

    private SiteUser joinWithSocialLogin(String oauthType, String loginID, String email, String nickname, String profileImgUrl) {
        return userService.join(oauthType, loginID, email, nickname, profileImgUrl);
    }
}