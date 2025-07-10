package com.ifsc.rautazap.wsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.ifsc.rautazap.wsmanagement.infra.mongo")
@EnableRedisRepositories(basePackages = "com.ifsc.rautazap.wsmanagement.infra.redis")
public class WsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsManagementApplication.class, args);
	}

}
