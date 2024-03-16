package com.doNotWorry.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {


    @Query("SELECT cr.foodName FROM Menu cr WHERE cr.foodDatas.id = :id")
    List<String> findFoodNameByID(@Param("id") Integer id);

}
