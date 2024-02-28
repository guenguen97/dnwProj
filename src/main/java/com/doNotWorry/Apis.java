package com.doNotWorry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Apis {

    @Value("${naver.map.api.key}") // Get the API key from application.properties
    private String naverMapApiKey;

//    @Value("${chatgpt.api-key}")
//    private String openaiApiKey;
    @GetMapping("/get_naver_map_key")
    public ApiResponse getNaverMapKey() {
        return new ApiResponse(naverMapApiKey);
    }

//    public String getOpenaiApiKey() {
//        return openaiApiKey;
//    }



    // Additional controllers and methods
}