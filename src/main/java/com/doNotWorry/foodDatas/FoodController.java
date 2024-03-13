package com.doNotWorry.foodDatas;


import com.doNotWorry.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FoodController {
    private final CsvService csvService;

    @Autowired
    private FoodService foodService;



    @GetMapping("/saveDataDB")
    public String saveDataToDB() throws IOException {

        List<List<String>> csvData2=  csvService.csvData("templates/data/dajeon_food.csv");
        foodService.saveDateToDB(csvData2);



        return "map";



    }



}
