package com.tenpo.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {
	public static void main(String[] args) {
		System.out.println("Init App v2");
		SpringApplication.run(ChallengeApplication.class, args);
	}
}
