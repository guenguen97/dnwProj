package com.doNotWorry.foodDatas;


import com.doNotWorry.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.doNotWorry.Main.isFloat;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MenuService menuService;


    public void saveDateToDB(List<List<String>> datas) {
        //지금은 너무 많아서 저장하는데 오래걸림 일단 양을 줄이자
        for (int i = 1; i < datas.size() / 20; i++) {
            FoodDatas foodDatas = new FoodDatas();

            //가게 이름 저장
            foodDatas.setName(datas.get(i).get(1));

            foodDatas.setAddress(datas.get(i).get(2));

            foodDatas.setStorePhNum(datas.get(i).get(4));

            double lat = 0;
            double lon = 0;

            //빈값이거나 숫자형태 아닌거는 취소
            if (datas.get(i).get(8) != "" && datas.get(i).get(9) != "" &&
                    isFloat(datas.get(i).get(8)) && isFloat(datas.get(i).get(9))) {
                lat = Double.parseDouble(datas.get(i).get(8));
                lon = Double.parseDouble(datas.get(i).get(9));

            }


            //위도 저장
            foodDatas.setLatitude(lat);
            //경도 저장
            foodDatas.setLongitude(lon);


            foodRepository.save(foodDatas);

            //지금 csv 데이터에 오류?가 있음
            //,"[갈비짬뽕,고기짬뽕,숯불고기짬뽕]" 이런 메뉴 데이터가 있고
            //,"['갈비짬뽕'; '고기짬뽕'; '숯불고기짬뽕']" 이런 메뉴 데이터가 있다.
            //첫번째 경우는 String 변수가 3개이다. , 이게 나올때마다 새로운 String이 나오는거임;;
            //2번쨰 경우는 String 변수가 하나의 형태이다.
            // 그래서 2개의 경우를 if 문을 통해서 따로 저장하는방식을 달리
            //해야 겠다.

            String foodName=datas.get(i).get(11);
            // "[갈비짬뽕,고기짬뽕,숯불고기짬뽕]"  이런 형태의 시작점 찾기
            // " 로 시작하고  중간에 ' 가 없는 형태 찾는거임
            // 메뉴가 혹시나 2글자 인거 올수도 있으니
            if (datas.get(i).get(11).length() > 3) {
                System.out.println("3글자 이상 메뉴인경우");

                if (datas.get(i).get(11).charAt(3) != '\'' &&
                        datas.get(i).get(11).charAt(0) == '\"') {
                    //이어진 Stirng 메뉴 찾기 위해
                    System.out.println("배열형태 아닌 메뉴 찾기");

                    int j = 0;//이어진 메뉴 계속 찾으려고 변수 투입

                    //마지막에 숯불고기짬뽕]"  이것처럼 "이걸로 끝나면 끝나는거니
                    //while문에  그 조건 넣음
                    while (datas.get(i).get(11 + j).charAt(datas.get(i).get(11 + j).length() - 1) != '\"') {
                        System.out.println("배열형태 아닌 메뉴들에 이어진 메뉴 들 찾기 ");

                        String b= datas.get(i).get(11 + j);
                        System.out.println(b);
                        menuService.create2(datas.get(i).get(11 + j), foodDatas);
                        j++;


                    }
                } else {

                    System.out.println("배열형태인 메뉴 찾음 ");
                    menuService.create(datas.get(i).get(11), foodDatas);


                }

            } else {
                menuService.create(datas.get(i).get(11), foodDatas);
            }

        }
    }

    public List<FoodDatas> getAllDatas(){
        return foodRepository.findAll();

    }

}
