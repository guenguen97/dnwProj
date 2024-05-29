package com.doNotWorry.communication;

import com.doNotWorry.rq.Rq;
import com.doNotWorry.user.SiteUser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    private Rq rq;
    @Autowired
    private  CommunicationService communicationService;

    @GetMapping("/write")
    public String write(){
        return "communicationTool";
    }

    @PostMapping("/write2")
    @ResponseBody
    public String writePost( @RequestBody Map<String, String> requestBody){
        SiteUser user=rq.getLoginUser();
        String title = requestBody.get("title");
        String content = requestBody.get("content");
        System.out.println(title+"!!!!!!!!!!!!!!!!!!!!!!!!!!");

        communicationService.create(user,title,content);

        return "{\"message\": \"success\"}";
    }
}
