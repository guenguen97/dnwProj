package com.doNotWorry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.doNotWorry.Main.isFloat;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;


    public void saveDateToDB(List<List<String>> datas){
        for (int i = 1; i < datas.size(); i++) {
            FoodDatas foodDatas =new FoodDatas();

            //가게 이름 저장
            foodDatas.setName(datas.get(i).get(1));

            foodDatas.setAddress(datas.get(i).get(2));

            foodDatas.setStorePhNum(datas.get(i).get(4));

            double lat=0;
            double lon=0;

            //빈값이거나 숫자형태 아닌거는 취소
            if(datas.get(i).get(8) != "" && datas.get(i).get(9) != "" &&
                    isFloat(datas.get(i).get(8)) && isFloat(datas.get(i).get(9))){
                lat = Double.parseDouble(datas.get(i).get(8));
                lon = Double.parseDouble(datas.get(i).get(9));

            }
            //위도 저장
            foodDatas.setLatitude(lat);
            //경도 저장
            foodDatas.setLongitude( lon);

            foodRepository.save(foodDatas);
        }

    }


    public List<FoodDatas> getAllDatas(){
        return foodRepository.findAll();

    }

}
