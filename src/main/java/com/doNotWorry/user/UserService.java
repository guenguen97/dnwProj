package com.doNotWorry.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void create(UserCreateForm userCreateForm) {
        SiteUser user =new SiteUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword1()));
        user.setEmail(userCreateForm.getEmail());
        user.setLoginID(userCreateForm.getLoginID());
        user.setNickName(userCreateForm.getNickName());
        user.setCreateDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Transactional
    public SiteUser join(String oauthType, String loginID, String email, String nickname, String profileImgUrl) {
        SiteUser user=new SiteUser();
        user.setOauthType(oauthType);

        user.setPassword("");
        user.setLoginID(loginID);
        user.setProfileImgUrl(profileImgUrl);
        user.setNickName(nickname);


        userRepository.save(user);

        return user;
    }


    public SiteUser getUserByLoginID(String loginID){
        if(userRepository.findByLoginID(loginID).get()==null){
            System.out.println("유저 정보 없음!!!!!!!");
            return null;
        }
       else {
        return  userRepository.findByLoginID(loginID).get();
       }
    }

    public int countSiteUserByLoginID(String loginID) {
       return userRepository.countByLoginID(loginID);
    }
}
