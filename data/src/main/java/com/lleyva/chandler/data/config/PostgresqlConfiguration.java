package com.lleyva.chandler.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource({"classpath:postgresql.properties"})
@EntityScan("com.lleyva.chandler.data")
@EnableJpaRepositories("com.lleyva.chandler.data.repositories")
public class PostgresqlConfiguration { }
