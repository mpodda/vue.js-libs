package com.mpodda.Vue.jsLibsDemo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.mpodda.Vue.jsLibsDemo.domain"})
@EnableJpaRepositories(basePackages = {"com.mpodda.Vue.jsLibsDemo.repository"})
public class RepositoryConfiguration {
	
}