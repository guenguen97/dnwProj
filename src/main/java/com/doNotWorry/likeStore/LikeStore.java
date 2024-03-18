package com.doNotWorry.likeStore;


import com.doNotWorry.foodDatas.FoodDatas;
import com.doNotWorry.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class LikeStore {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer foodDataId;

    @ManyToOne
    private SiteUser siteUser;


}
