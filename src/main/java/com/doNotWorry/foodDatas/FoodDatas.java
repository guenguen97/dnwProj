package com.doNotWorry.foodDatas;


import com.doNotWorry.menu.Menu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(indexes = {
        @Index(name = "idx_latitude", columnList = "latitude"),
        @Index(name = "idx_longitude", columnList = "longitude")
})
public class FoodDatas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private String storePhNum;

    private double latitude;

    private double longitude;


    @OneToMany(mappedBy = "foodDatas" , cascade = CascadeType.REMOVE)
    private List<Menu> menu;




}
