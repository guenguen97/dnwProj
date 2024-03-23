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

    @PostMapping("like/store/{id}/{groupName}")
    @ResponseBody
    public String likeStore(@PathVariable(name = "id") final Integer id,
                            @PathVariable(name = "groupName") final String groupName,  Principal principal){
        System.out.println("메뉴 저장하기 성공");
        if(principal ==null){
            return "{\"message\": \"not login\"}";
        }
        SiteUser user =userService.getUserByLoginID(principal.getName());
        System.out.println(groupName+"!!!!!!!!!!!!!!!!!!!!!!!!!!");


        FoodDatas foodData= foodService.getDataById(id);

        //중복된 저장 현황이 있는지 체크
        if(likeStoreService.countLikeStore(user,foodData.getId(), groupName) >0){
            return "{\"message\": \"중복\"}";
        };
        likeStoreService.saveStore(user,foodData.getId(),foodData.getName(),groupName);





        return "{\"message\": \"success\"}";

    }





}
