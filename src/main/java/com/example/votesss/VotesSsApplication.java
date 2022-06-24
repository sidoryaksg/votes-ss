package com.example.votesss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class VotesSsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotesSsApplication.class, args);
	}

}
