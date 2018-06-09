package com.lleyva.chandler.data.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.lleyva.chandler.data"})
@PropertySource({"classpath:data.properties"})
@Import({PostgresqlConfiguration.class})
public class DataConfiguration { }
