package com.upgrad.musichoster.api;


import com.upgrad.musichoster.service.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ServiceConfiguration.class)
public class MusicApiApplication {
	public static void main(String[] args) {

		SpringApplication.run(MusicApiApplication.class, args);
	}
}
