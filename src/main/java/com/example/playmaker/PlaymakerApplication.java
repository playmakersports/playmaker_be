package com.example.playmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlaymakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaymakerApplication.class, args);
	}

}
