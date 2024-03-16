package com.doNotWorry.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void create(UserCreateForm userCreateForm) {
        SiteUser user =new SiteUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword1()));

        user.setLoginID(userCreateForm.getLoginID());
        user.setUserName(userCreateForm.getUserName());
        user.setCreateDate(LocalDateTime.now());
        userRepository.save(user);
    }
}
