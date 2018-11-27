package com.lleyva.chandler.services.config;

import com.lleyva.chandler.data.config.DataConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = {"com.lleyva.chandler.services"})
@Import({DataConfiguration.class})
public class ServicesConfiguration {

	//////////////////////
	// Bean Definitions //
	//////////////////////

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(8);

	}

}
