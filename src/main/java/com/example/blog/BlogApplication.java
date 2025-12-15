package com.example.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
        // System.setProperty("OAUTH2_CLIENT_ID", dotenv.get("OAUTH2_CLIENT_ID"));
        // System.setProperty("OAUTH2_CLIENT_SECRET", dotenv.get("OAUTH2_CLIENT_SECRET"));
		System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
        System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
		SpringApplication.run(BlogApplication.class, args);
	}

}
