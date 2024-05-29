package com.doNotWorry.communication;

import com.doNotWorry.rq.Rq;
import com.doNotWorry.user.SiteUser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/write")
    @ResponseBody
    public String writePost( @RequestParam(value="title") String title, @RequestParam(value="content") String content){
        SiteUser user=rq.getLoginUser();
        communicationService.create(user,title,content);

        return "성공";
    }
}
