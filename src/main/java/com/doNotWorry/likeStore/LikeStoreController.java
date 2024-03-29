package com.doNotWorry.likeStore;


import com.doNotWorry.foodDatas.FoodDatas;
import com.doNotWorry.foodDatas.FoodService;
import com.doNotWorry.user.SiteUser;
import com.doNotWorry.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.*;

@Controller
public class LikeStoreController {
    @Autowired
    private UserService userService;

    @Autowired
    private LikeStoreService likeStoreService;

    @Autowired
    private FoodService foodService;

    //음식점 즐겨찾기 기능
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

            likeStoreService.likeStoreByFoodDataIdAndGroupNameAndSiteUser(user,foodData.getId(), groupName);
            likeStoreService.deleteByFoodDataIdAndGroupNameAndSiteUser(user,foodData.getId(), groupName);
            return "{\"message\": \"삭제\"}";
        };
        likeStoreService.saveStore(user,foodData.getId(),foodData.getName(),groupName);

        return "{\"message\": \"success\"}";

    }


    @GetMapping("/loginUser/groupName")
    @ResponseBody
    public LinkedHashSet<String> findLoginUserGroup(Principal principal){
        SiteUser user =userService.getUserByLoginID(principal.getName());
        return likeStoreService.findGroupByUser(user);


    }


    //즐겨찾기한 음식점 리스트 보여주는 페이지
    @GetMapping("/likeStore/detail/groupName={groupName}")
    public String likeStoreDetail(@PathVariable("groupName") String groupName, Model model){
        System.out.println(groupName);
        model.addAttribute("groupName" ,groupName);
        return "likeStoreDetail";
    }

    //즐겨찾기한 음식점 리스트 페이지에서 음식점 리스트 갖고와주는api
    @GetMapping("/likeStore/detail/{groupName}")
    @ResponseBody
    public List<String> likeStoreDetailJSON(@PathVariable("groupName") String groupName, Model model,
                                      Principal principal){
        System.out.println(groupName);
        SiteUser user =userService.getUserByLoginID(principal.getName());
        model.addAttribute("groupName" ,groupName);
        List<String> storeList = likeStoreService.findStoreByGroupName(groupName, user);

        System.out.println(storeList.get(0)+"!!!!!!!!!!!!!!!!");

        return storeList;
    }


    @GetMapping("/likeStore/random/{groupName}")
    @ResponseBody
    public List<String> likeStoreRandom(@PathVariable("groupName") String groupName,
                                  Principal principal){
        SiteUser user =userService.getUserByLoginID(principal.getName());
        List<String> storeList = likeStoreService.findStoreByGroupName(groupName, user);
        Random random = new Random();
        int n= storeList.size();

        //0 이상 n 미만의 수  생성
        int randomNumber = random.nextInt(n);
        List<String> result = new ArrayList<>();
        result.add(storeList.get(randomNumber));
        return result;

    }

    @GetMapping("/likeStore/count/groupName/{id}")
    @ResponseBody
    public  HashMap<String, Integer> countLikeStore(@PathVariable(name = "id") Integer id,
                                       Principal principal){
        SiteUser user =userService.getUserByLoginID(principal.getName());
        HashMap<String, Integer> countEachGroupStore = new HashMap<>();
        List<LikeStore> storeList=likeStoreService.findBySiteUserAndFoodDataId(user,id);
        for (int i = 0; i < storeList.size(); i++) {
            if(storeList.get(i).getGroupName().equals("맛집")){
                countEachGroupStore.put("맛집" ,1);
            }else if(storeList.get(i).getGroupName().equals("가보고 싶은 곳")){
                countEachGroupStore.put("가보고 싶은 곳",1);
            }else if(storeList.get(i).getGroupName().equals("기타")){
                countEachGroupStore.put("기타" , 1);
            }
        }
        return countEachGroupStore;

    }





}
