package com.example.demo;

import com.example.demo.config.OpenApiProperties;
import com.example.demo.config.ServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableConfigurationProperties({
	OpenApiProperties.class,
	ServerProperties.class
})


@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaRepositories
@CrossOrigin
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}