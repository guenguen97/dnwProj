package com.doNotWorry.foodDatas;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDatasDTO {


    private Integer id;

    private String name;

    private String address;

    private String storePhNum;

    private double latitude;

    private double longitude;


    public FoodDatasDTO(FoodDatas foodDatas) {
        this.id = foodDatas.getId();
        this.name = foodDatas.getName();
        this.address = foodDatas.getAddress();
        this.storePhNum = foodDatas.getStorePhNum();
        this.latitude = foodDatas.getLatitude();
        this.longitude = foodDatas.getLongitude();
    }
}
