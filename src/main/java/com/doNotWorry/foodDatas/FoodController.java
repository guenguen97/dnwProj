package com.doNotWorry.foodDatas;


import com.doNotWorry.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class FoodController {
    private final CsvService csvService;

    @Autowired
    private FoodService foodService;



    @GetMapping("/saveDataDB")
    @ResponseBody
    public String saveDataToDB() throws IOException {

        List<List<String>> csvData2=  csvService.csvData("templates/data/dajeon_food.csv");
        foodService.saveDateToDB(csvData2);

        return "map";

    }

    @PostMapping("/search/foodData/{searchString}")
    @ResponseBody
    public  List<FoodDatasDTO> searchString(@PathVariable("searchString") final String searchString){
        List<FoodDatas> foodDatas=foodService.getDataBySearch(searchString);
        List<FoodDatasDTO> datasDTOS= new ArrayList<>();

        //빈 DTO 에  음식점 데이터 넣는 작업
        for (int i = 0; i < foodDatas.size(); i++) {
            FoodDatasDTO dto= new FoodDatasDTO( foodDatas.get(i));
//            datasDTOS.add(dto);
            datasDTOS.add(dto);
        }

        return datasDTOS;

    }



    @GetMapping("/myRandomPick")
    @ResponseBody
    public FoodDatasDTO myRandomPick(){
       
		FoodDatasDTO datasDTOS=foodService.getRanDomData();

	  return datasDTOS;

    }



}
