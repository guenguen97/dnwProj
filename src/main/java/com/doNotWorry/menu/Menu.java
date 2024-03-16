package com.doNotWorry.menu;


import com.doNotWorry.foodDatas.FoodDatas;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Menu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private FoodDatas foodDatas;

    private String foodName;


}
