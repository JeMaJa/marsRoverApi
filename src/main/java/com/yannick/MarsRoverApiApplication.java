package com.yannick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class MarsRoverApiApplication extends SpringBootServletInitializer  {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MarsRoverApiApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(MarsRoverApiApplication.class, args);
	}

}
