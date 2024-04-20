package com.doNotWorry;

import com.doNotWorry.foodDatas.FoodDatas;
import com.doNotWorry.foodDatas.FoodDatasDTO;
import com.doNotWorry.foodDatas.FoodService;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class Main {
    private final CsvService csvService;
    private final FoodService foodService;

    @Autowired
    private Apis apis;

    @GetMapping("/")
    public String main(Model model) throws IOException {

            System.out.println("테스트 시작 ");

        return "map";
    }

    @GetMapping("/test")
    @ResponseBody
    public String dd(){

        return "{\"message\": \"success\"}";
    }

    @PostMapping("/stop/newsPopup")
    @ResponseBody
    public String stopPopup(HttpServletResponse res,HttpServletRequest request){
        System.out.println("쿠키 생성");
        Cookie cookie = new Cookie("newsPopup","stop");

        cookie.setDomain("localhost");
        cookie.setPath("/");
        // 하루간 팝업 정지
        cookie.setMaxAge(60*60*24);
        cookie.setSecure(true);
        cookie.setHttpOnly(true); // JavaScript로 쿠키에 접근 방지
        res.addCookie(cookie);

        ResponseCookie.from("newsPopupByResponseCookie", "stop")
                .domain(".donotworry.site")
                .maxAge(60*60*24)
                .sameSite("None")
                .path("/")
                .build().toString();



        return "소식 팝업 정지";
    }

    @GetMapping("/newsCookies")
    @ResponseBody
    public String getNewsCookies(HttpServletRequest req){
        System.out.println("asdasd");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("newsPopup")){
                    System.out.println("쿠키 있음");
                    //팝업 정지를 원하는 쿠키가 존재하면 참 반환
                    return "{\"message\": \"true\"}";
                }
            }
        }
        System.out.println("쿠키 없음");

        return "{\"message\": \"false\"}";
    }

    @GetMapping("getNearFood")
    @ResponseBody
    public List<FoodDatasDTO> getNearFood(@RequestParam(name = "lat") float lat,
                                          @RequestParam(name = "har") float har) throws IOException {
        //현재 위치 또는, 현재 지도의 중심의 위도 경도를 받아서 시작함
        System.out.println(lat+" and"+har+"정보 받기 성공");


        //JPA 를 이용해서 편리하게 쿼리문없이 바로 위도 경도범위내 음식점 찾기
        List<FoodDatas> foodDatasListByJPA =foodService.getDatasInRange(lat-0.0010045F, lat+0.0010045F, har-0.0026045F, har+0.0026045F);

//        List<List<String>> csvData2=  csvService.csvData("C:\\work\\dajeon_food.csv");
        List<List<String>> csvData2=  csvService.csvData("templates/data/dajeon_food.csv");

        List<List<String>> mainData = new ArrayList<>();

        //DB 에서 갖고온 데이타 버전
        List<FoodDatasDTO> mainData2 = new ArrayList<>();


        System.out.println("????????????????????");



        for (int i = 0; i <mainData.size() ; i++) {
            System.out.println(mainData.get(i).get(3));
        }


        List<FoodDatasDTO> datasDTOS= new ArrayList<>();


        List<FoodDatas> datas=foodService.getAllDatas();

        //빈 DTO 에  음식점 데이터 넣는 작업
        for (int i = 0; i < foodDatasListByJPA.size(); i++) {
            FoodDatasDTO dto= new FoodDatasDTO( foodDatasListByJPA.get(i));
//            datasDTOS.add(dto);
            mainData2.add(dto);
        }


        //DTO 에서 현재 위치 근처 음식점만 따로 찾아서 main데이터에 넘기는 작업
//        for (int i = 0; i < datasDTOS.size(); i++) {
//            if(datasDTOS.get(i).getLatitude() !=0 && datasDTOS.get(i).getLongitude() !=0){
//                //특정 위치에서 범위 설정하는거
//                if((datasDTOS.get(i).getLatitude()< lat+0.0009045 && datasDTOS.get(i).getLatitude() >lat-0.0009045)&&
//                        (datasDTOS.get(i).getLongitude()<har+0.0025045 && datasDTOS.get(i).getLongitude()>har-0.0025045) ){
//                    System.out.println("메인 데이타에 데이타 추가중 ");
//
//                    mainData2.add(datasDTOS.get(i));
//                }
//            }
//        }




//        return mainData;
            return mainData2;



    }


//String 변수가 float 형태 인지 확인하는거
    public static boolean isFloat(String value) {
        try {
            // Try parsing the String as a float
            Float.parseFloat(value);
            return true; // Parsing successful, it's a float
        } catch (NumberFormatException e) {
            return false; // Parsing failed, not a float
        }
    }


}


