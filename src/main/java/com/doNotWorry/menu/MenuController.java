package com.doNotWorry.menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;



    @GetMapping("/menu/{id}")
    @ResponseBody
    public List<String> getMenu(@PathVariable(name = "id") final Integer id){
        System.out.println("메뉴 불러오기 api 실행");
        List<String> menuList= menuService.findMenuListById(id);
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(menuList.get(i));
        }


        return menuList;
    }
}
