package com.doNotWorry.user;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    private String dirPath;

    private final PasswordEncoder passwordEncoder;

    @Value("${custom.genFile.dirPath}")
    public void setUploadDir(String dirPath) {
        this.dirPath = dirPath;
    }


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


    @Transactional
    public void updateUser(SiteUser user,String nickName, String email) {
        if (nickName != null && nickName.length() > 0) {
          user.setNickName(nickName);
        }

        if (email != null && email.length() > 0) {
            user.setEmail(email);
        }

        user.setUpdateDate(LocalDateTime.now());
        userRepository.save(user);
    }

    public boolean isCorrectPassword(SiteUser user, String password) {
        String actualPassword = user.getPassword();
        return passwordEncoder.matches(password, actualPassword);
    }

    public void updatePassword(SiteUser user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void addNewProfileImg(SiteUser user, MultipartFile file) throws IOException {
        File directory = new File(dirPath);


        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리가 없으면 생성
        }
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(directory, fileName);
        file.transferTo(saveFile);

        user.setProfileImgUrl("/gen/" + fileName);
        userRepository.save(user);
    }
}
