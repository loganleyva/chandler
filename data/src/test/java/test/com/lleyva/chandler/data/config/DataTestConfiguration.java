package test.com.lleyva.chandler.data.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@PropertySource({"classpath:data.properties", "classpath:postgresql.properties"})
@EntityScan("com.lleyva.chandler.data")
@EnableJpaRepositories("com.lleyva.chandler.data.repositories")
public class DataTestConfiguration { }
