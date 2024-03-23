package com.doNotWorry.likeStore;


import com.doNotWorry.foodDatas.FoodDatas;
import com.doNotWorry.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class LikeStore {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer foodDataId;

    private String groupName;

    private LocalDateTime createDate;

    @ManyToOne
    private SiteUser siteUser;


}
