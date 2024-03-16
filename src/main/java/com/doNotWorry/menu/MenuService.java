package com.doNotWorry.menu;


import com.doNotWorry.foodDatas.FoodDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    //배열 형태의 String 을 받는 곳
    public void create(String menu , FoodDatas foodDatas){

        List<String> menuList= divideString(menu);

        for (int i = 0; i < menuList.size(); i++) {
            System.out.println("메뉴 테이블에 데이터 추가");

            Menu menu1 = new Menu();
            menu1.setFoodName(menuList.get(i));
            menu1.setFoodDatas(foodDatas);

            menuRepository.save(menu1);
        }

    }

    // 한 단어 형태 String 만   받는 곳
    public void create2(String menu ,FoodDatas foodDatas ){
        String result= "ㅇㅇ";

        if (menu.contains("[") ||menu.contains("]")  ) {
            result = menu.replaceAll("\"", "").trim();
            result = result.replaceAll("]", "").trim();
            result = result.replaceAll("\\[", "").trim();
        } else {
            result = menu;
        }


        Menu menu1 = new Menu();
        menu1.setFoodName(result);
        menu1.setFoodDatas(foodDatas);

        menuRepository.save(menu1);

    }


    public List<String> divideString(String inputString){
        // Regular expression to match strings within single quotation marks
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(inputString);

        List<String> resultList = new ArrayList<>();

        // Find all matches and add them to the resultList
        while (matcher.find()) {
            resultList.add(matcher.group(1)); // Group 1 contains the string within the single quotes
        }

        return resultList;




    }


    public List<String> findMenuListById(Integer id) {
       return menuRepository.findFoodNameByID(id);
    }
}
