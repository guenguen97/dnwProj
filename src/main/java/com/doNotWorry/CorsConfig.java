package com.doNotWorry;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT","PATCH", "DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }

    //로그인 ,회원 가입 아이디 중복 체크, 회원 가입 완료 api 는 예외 처리 해두기
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoggerInterceptor())
//                .excludePathPatterns("/css/**", "/images/**", "/js/**",
//                        "/user/login","/user/siteUser-count","/user/signUp","/",
//                        "/api/get_naver_map_key","/getNearFood","/like/store/**",
//                        "/menu/**" ,"/user/**","/saveDataDB","/kakao/**");
//    }

}