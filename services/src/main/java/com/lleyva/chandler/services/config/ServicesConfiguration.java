package com.lleyva.chandler.services.config;

import com.lleyva.chandler.data.config.DataConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.lleyva.chandler.services"})
@Import({DataConfiguration.class})
public class ServicesConfiguration { }
