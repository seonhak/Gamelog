package com.best11.gamelog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GameLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameLogApplication.class, args);
	}

}
