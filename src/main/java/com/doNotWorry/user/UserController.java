package com.doNotWorry.user;

import com.doNotWorry.kakao.KakaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @Autowired
    private  UserService userService;
    private final KakaoService kakaoService;


    @GetMapping("/login")
    public String signUp(Model model){
        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());

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

    @GetMapping("/myPage")
    public String myPage(){
        return "myPage";
    }

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
