package com.doNotWorry.foodDatas;


import com.doNotWorry.menu.Menu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(indexes = {
        @Index(name = "idx_latitude", columnList = "latitude"),
        @Index(name = "idx_longitude", columnList = "longitude"),
        @Index(name = "idx_name", columnList = "name")

})
public class FoodDatas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private String storePhNum;


    //double 대신 float 를 쓴 이유
    //double 이 f 보다 더 정밀한 표현이 가능함
    // 근데 더 많은 메모리도 소비함
    //엄청 큰 규모가 아니라 상관이없을수도있는데
    // 그래도 2만개의 데이터를 쓰기 때문에
    // 안전하게 구동하기위해 float 를씀 그리고 소수점 자리도 충분함
    private double latitude;

    private double longitude;


    @OneToMany(mappedBy = "foodDatas" , cascade = CascadeType.REMOVE)
    private List<Menu> menu;




}
