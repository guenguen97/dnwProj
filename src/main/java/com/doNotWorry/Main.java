package com.doNotWorry;

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

    @Autowired
    private Apis apis;

//    @GetMapping("/chat")
//    public String chatPage() {
//        return "chat";
//    }

    @GetMapping("/")
    public String main(Model model) throws IOException {
        try {
            List<List<String>> csvData1=  csvService.csvData("C:\\work\\food_data.csv");
            List<List<String>> csvData2=  csvService.csvData("C:\\work\\dajeon_food.csv");

            model.addAttribute("fData",csvData1);
            model.addAttribute("fData2", csvData2);

            System.out.println("테스트 시작 ");
            System.out.println(csvData1.get(0).get(0));

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your application's requirements
        }
        model.addAttribute("openaiApiKey", apis.getOpenaiApiKey());


        return "map";
    }


    @GetMapping("getNearFood")
    @ResponseBody
    public List<List<String>> getNearFood( @RequestParam(name = "lat") float lat,
                               @RequestParam(name = "har") float har) throws IOException {

        System.out.println(lat+" and"+har+"정보 받기 성공");

        List<List<String>> csvData2=  csvService.csvData("C:\\work\\dajeon_food.csv");

        List<List<String>> mainData = new ArrayList<>();;
        System.out.println("????????????????????");

//        for(int i=1; i<=csvData2.size()/2;i++){
//            float lat2 = 0;
//            float har2=0;
//
//            if(csvData2.get(i).get(8) != "" && csvData2.get(i).get(9) != "" &&
//                     isFloat(csvData2.get(i).get(8)) && isFloat(csvData2.get(i).get(9))){
//             lat2 = Float.parseFloat(csvData2.get(i).get(8));
//             har2 = Float.parseFloat(csvData2.get(i).get(9));
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//
//            }
//
//            if(lat2 !=0 && har2 !=0){
//                if((lat2< lat+0.0005045 && lat2 >lat-0.0005045)&&(har2<har+0.0045045 && har2>har-0.0045045) ){
//                    System.out.println("메인 데이타에 데이타 추가중 ");
//                    csvData2.get(i).get(2);
//                    mainData.add(csvData2.get(i));
//                }
//            }
//
//
//        }


        for (int i = 0; i <mainData.size() ; i++) {
            System.out.println(mainData.get(i).get(3));
        }

//
//        for(int i=csvData2.size()/2+1; i<=csvData2.size()-3;i++){
//            float lat2 = 0;
//            float har2=0;
//            if(csvData2.get(i).get(8) != "" && csvData2.get(i).get(9) != "" &&
//                    isFloat(csvData2.get(i).get(8)) && isFloat(csvData2.get(i).get(9))){
//                lat2 = Float.parseFloat(csvData2.get(i).get(8));
//                har2 = Float.parseFloat(csvData2.get(i).get(9));
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//
//            }
//            if(lat2 !=0 && har2 !=0){
//                if((lat2< lat+0.0025045 && lat2 >lat-0.0025045)&&(har2<har+0.0145045 && har2>har-0.0145045) ){
//                    System.out.println("메인 데이타에 데이타 추가중 ");
//                    csvData2.get(i).get(2);
//                    mainData.add(csvData2.get(i));
//                }
//            }
//
//
//        }

        for(int i=csvData2.size()/2+1; i<=csvData2.size()-3;i++){
            float lat2 = 0;
            float har2=0;
            if(csvData2.get(i).get(8) != "" && csvData2.get(i).get(9) != "" &&
                    isFloat(csvData2.get(i).get(8)) && isFloat(csvData2.get(i).get(9))){
                lat2 = Float.parseFloat(csvData2.get(i).get(8));
                har2 = Float.parseFloat(csvData2.get(i).get(9));
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!");

            }
            if(lat2 !=0 && har2 !=0){
                if((lat2< lat+0.0025045 && lat2 >lat-0.0025045)&&(har2<har+0.0145045 && har2>har-0.0145045) ){
                    System.out.println("메인 데이타에 데이타 추가중 ");
                    csvData2.get(i).get(2);
                    mainData.add(csvData2.get(i));
                }
            }


        }


        return mainData;




    }



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


