package com.doNotWorry.foodDatas;


import com.doNotWorry.menu.Menu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class FoodDatas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String address;

    String storePhNum;

    double latitude;

    double longitude;


    @OneToMany(mappedBy = "foodDatas" , cascade = CascadeType.REMOVE)
    private List<Menu> menu;




}
