package com.doNotWorry.user;


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

    private String loginID;

    private String password;

    private String userName;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "siteUser" , cascade = CascadeType.REMOVE)
    private List<LikeStore> likeStore;



}
