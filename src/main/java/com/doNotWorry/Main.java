package com.doNotWorry;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class Main {
    private final CsvService csvService;




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


        return "map";
    }








}


