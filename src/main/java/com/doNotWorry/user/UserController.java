package com.doNotWorry.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private  UserService userService;


    @GetMapping("/login")
    public String signUp(){

        return "login";
    }

    @GetMapping("/myPage")
    public String myPage(){
        return "myPage";
    }


    //회원 가입 용
    @PostMapping("/signUp" )
    public String signUp( @Valid @RequestBody UserCreateForm userCreateForm){
        System.out.println("정보 받기 성공!!!!!!!!!!");

        userService.create(userCreateForm);

        return "main";
    }


    //아이디 중복 체크용
//    @GetMapping("/siteUser-count")
//    @ResponseBody
//    public int countMemberByLoginId(@RequestParam (name = "loginID") final String loginID) {
//        System.out.println(loginID+"!!!!!!!!!!!!!!!!!!!!!!!!");
//        return userService.countSiteUserByLoginID(loginID);
//    }


    @GetMapping("/changeInfor")
    public String changeInfor(){
        return "changeInfor";
    }


    // 회원 정보 수정
//    @PatchMapping("/information/{id}")
//    @ResponseBody
//    public Long updateMember(@PathVariable(name = "id") final Long id, @RequestBody final SiteUserRequest params) {
//        System.out.println("회원 정보 수정 시작!!!!!!!!!!!!!!");
//        System.out.println(params.getUserName());
//        params.setId(id);
//        System.out.println(params.getId());
//        System.out.println(params.getEmail());
//        return userService.updateUser(params);
//    }



    //로그인한 회원 정보 갖고오기
//    @GetMapping("/information")
//    @ResponseBody
//    public SiteUserResponse getLoginUser(Principal principal){
//        SiteUserResponse user = userService.findUserByLoginID(principal.getName());
//
//        return user;
//
//    }


}
