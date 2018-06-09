package com.lleyva.chandler.web;

import com.lleyva.chandler.web.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfiguration.class)
public class ChandlerApplication {

	/**
	 * Starter of all the things!
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ChandlerApplication.class, args);
	}
	
}