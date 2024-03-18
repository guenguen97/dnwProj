package com.doNotWorry.likeStore;


import com.doNotWorry.foodDatas.FoodDatas;
import com.doNotWorry.foodDatas.FoodService;
import com.doNotWorry.user.SiteUser;
import com.doNotWorry.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class LikeStoreController {
    @Autowired
    private UserService userService;

    @Autowired
    private LikeStoreService likeStoreService;

    @Autowired
    private FoodService foodService;

    @PostMapping("like/store/{id}")
    @ResponseBody
    public String likeStore(@PathVariable(name = "id") final Integer id,
                                            Principal principal){
        System.out.println("메뉴 저장하기 성공");
        if(principal ==null){
            return "{\"message\": \"not login\"}";
        }
        SiteUser user =userService.getUserByLoginID(principal.getName());


        FoodDatas foodData= foodService.getDataById(id);


        likeStoreService.saveStore(user,foodData.getId(),foodData.getName());





        return "{\"message\": \"success\"}";

    }





}
