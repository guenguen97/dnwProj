package com.doNotWorry.menu;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuController {



    @GetMapping("/menu")
    @ResponseBody
    public String getMenu(@PathVariable(name = "id") final Long id){

        return "굿"+id;
    }
}
