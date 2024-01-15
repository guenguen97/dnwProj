package com.doNotWorry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NaverMapController {

    @Value("${naver.map.api.key}") // Get the API key from application.properties
    private String naverMapApiKey;

    @GetMapping("/get_naver_map_key")
    public ApiResponse getNaverMapKey() {
        return new ApiResponse(naverMapApiKey);
    }

    // Additional controllers and methods
}