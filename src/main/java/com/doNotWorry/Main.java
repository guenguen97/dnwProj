package com.doNotWorry;

import com.doNotWorry.foodDatas.FoodDatas;
import com.doNotWorry.foodDatas.FoodDatasDTO;
import com.doNotWorry.foodDatas.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @GetMapping("/chat")
//    public String chatPage() {
//        return "chat";
//    }

    @GetMapping("/")
    public String main(Model model) throws IOException {

            System.out.println("테스트 시작 ");


//        model.addAttribute("openaiApiKey", apis.getOpenaiApiKey());


        return "map";
    }

    @GetMapping("/test")
    public String dd(){

        return "main";
    }


    @GetMapping("getNearFood")
    @ResponseBody
    public List<FoodDatasDTO> getNearFood(@RequestParam(name = "lat") float lat,
                                          @RequestParam(name = "har") float har) throws IOException {

        System.out.println(lat+" and"+har+"정보 받기 성공");

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

        for (int i = 0; i < datas.size(); i++) {
            FoodDatasDTO dto= new FoodDatasDTO( datas.get(i));
            datasDTOS.add(dto);
        }



        for (int i = 0; i < datasDTOS.size(); i++) {
            if(datasDTOS.get(i).getLatitude() !=0 && datasDTOS.get(i).getLongitude() !=0){
                //특정 위치에서 범위 설정하는거
                if((datasDTOS.get(i).getLatitude()< lat+0.0009045 && datasDTOS.get(i).getLatitude() >lat-0.0009045)&&
                        (datasDTOS.get(i).getLongitude()<har+0.0025045 && datasDTOS.get(i).getLongitude()>har-0.0025045) ){
                    System.out.println("메인 데이타에 데이타 추가중 ");

                    mainData2.add(datasDTOS.get(i));
                }
            }
        }




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


