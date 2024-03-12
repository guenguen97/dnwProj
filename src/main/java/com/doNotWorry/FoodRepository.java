package com.doNotWorry;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface  FoodRepository extends JpaRepository<FoodDatas , Integer > {



}
