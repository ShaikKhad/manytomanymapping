package com.jsp.Manytomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.jsp.SpringbootRestApiJPAManytoMany"})

public class ManytomanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManytomanyApplication.class, args);
	}

}
