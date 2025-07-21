package com.hexaware.web.apichallange;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApichallangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApichallangeApplication.class, args);
	}
	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}
	

}
