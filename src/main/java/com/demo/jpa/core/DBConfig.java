package com.demo.jpa.core;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfig {

	@Primary
	@Bean(name="jpaDatasource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource jpaDatasource() {
		return DataSourceBuilder.create().build();
	}
}
