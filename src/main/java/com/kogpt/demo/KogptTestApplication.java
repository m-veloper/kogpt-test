package com.kogpt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class KogptTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KogptTestApplication.class, args);
	}

}
