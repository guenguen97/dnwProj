package com.doNotWorry.foodDatas;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface  FoodRepository extends JpaRepository<FoodDatas , Integer > {


    List<FoodDatas> findByLatitudeBetweenAndLongitudeBetween(float minLat, float maxLat, float minLong, float maxLong);

    List<FoodDatas> findByNameContaining(String searchString);


    FoodDatas findByLatitudeNotAndId(double lat, Integer id);
}
