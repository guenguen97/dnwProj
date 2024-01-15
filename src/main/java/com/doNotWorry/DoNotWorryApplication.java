package com.doNotWorry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-secret.yml")

public class DoNotWorryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoNotWorryApplication.class, args);
	}

}
