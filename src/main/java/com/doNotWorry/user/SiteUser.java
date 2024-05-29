package com.doNotWorry.user;


import com.doNotWorry.communication.Communication;
import com.doNotWorry.likeStore.LikeStore;
import com.doNotWorry.menu.Menu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class SiteUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String loginID;

    private String password;

    private String nickName;

    private String oauthType;

    private String profileImgUrl;

    @Column(unique = true)
    private String email;


    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "siteUser" , cascade = CascadeType.REMOVE)
    private List<LikeStore> likeStore;


    public void updateWhenSocialLogin(String nickName, String profileImgUrl) {
        this.nickName = nickName;
        this.profileImgUrl = profileImgUrl;
    }

    @OneToMany(mappedBy = "siteUser" , cascade = CascadeType.REMOVE)
    private List<Communication> communications;


}
