package com.lleyva.chandler.web.config;

import com.lleyva.chandler.services.config.ServicesConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@ComponentScan(basePackages = {"com.lleyva.chandler.web"})
@Import({ServicesConfiguration.class})
public class WebConfiguration { }
