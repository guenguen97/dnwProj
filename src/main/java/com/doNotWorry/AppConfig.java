package com.doNotWorry;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Getter
    public static String genFileDirPath;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${custom.genFile.dirPath}")
    public void setFileDirPath(String genFileDirPath) {
        AppConfig.genFileDirPath = genFileDirPath;
    }
}
