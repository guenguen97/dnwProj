package com.doNotWorry.user;

//import com.doNotWorry.kakao.KakaoService;
import com.doNotWorry.rq.Rq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private  final Rq rq;


    @Autowired
    private  UserService userService;
//    private final KakaoService kakaoService;


//    @PreAuthorize("isAnonymous()") //로그아웃 상태만
    @GetMapping("/login")
    public String signUp(Model model){
//        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        return "login";
    }

    @GetMapping("/logined")
    @ResponseBody
    public String islogined(Principal principal){
        System.out.println("로그인 유무 검사 api 실행됨 !!!!!!!!!!!!!!!!");
        if(principal == null){
            System.out.println("로그인 안되어있음");
            return "{\"message\": \"not login\"}";
        }
        else {
            System.out.println("로그인 되어있음 ");

            return "{\"message\": \"success\"}";

        }
    }

    @PreAuthorize("isAuthenticated()") //로그인 상태만 되게
    @GetMapping("/myPage")
    public String myPage(Model model){
        SiteUser user=rq.getLoginUser();

        model.addAttribute("user",user);

        return "myPage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myStoreList")
    public String myStoreList(){
        return "myStoreList";
    }

    //회원 가입 용
    @PostMapping("/signUp" )
    @ResponseBody
    public String signUp(@Valid @RequestBody UserCreateForm userCreateForm, BindingResult bindingResult){
        System.out.println("정보 받기 성공!!!!!!!!!!");

        if (bindingResult.hasErrors()) {

            Map<String, String> errorMap = new HashMap<>();

            // 각 필드별 에러 메시지를 Map에 추가
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            // JSON 형태로 변환하여 클라이언트에게 반환
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
            return "실패";
        }

        userService.create(userCreateForm);

        return "main";
    }


    //아이디 중복 체크용
    @GetMapping("/siteUser-count")
    @ResponseBody
    public int countMemberByLoginId(@RequestParam (name = "loginID") final String loginID) {
        System.out.println(loginID+"!!!!!!!!!!!!!!!!!!!!!!!!");
        return userService.countSiteUserByLoginID(loginID);
    }

    //기본 회원 정보 변경



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/changeInformation")
    public String changeInformation(Model model){
        SiteUser user=rq.getLoginUser();

        model.addAttribute("user",user);

        return "changeInformation";
    }

    // 회원 정보 수정
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/information/{nickName}/{email}")
    @ResponseBody
    public String updateUser(@PathVariable(name = "nickName") final String nickName,
                           @PathVariable(name = "email") final String email ) {
        System.out.println("회원 정보 수정 시작!!!!!!!!!!!!!!");
        SiteUser user =rq.getLoginUser();

        userService.updateUser(user,nickName,email);

        return  "{\"message\": \"success\"}";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/changePassword")
    public String changePassword(){
        return "changePassword";
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/changePassword")
    @ResponseBody
    public String changePassword(@RequestBody Map<String, String> params){
        SiteUser user= rq.getLoginUser();
        String currentPassword = params.get("currentPassword");
        String newPassword1 = params.get("newPassword1");
        String newPassword2 = params.get("newPassword2");

        if(!userService.isCorrectPassword(user,currentPassword)){
            return  "{\"message\": \"fail\"}";
        }else {
            userService.updatePassword(user,newPassword1);
        }



        return "{\"message\": \"success\"}";
    }



}
