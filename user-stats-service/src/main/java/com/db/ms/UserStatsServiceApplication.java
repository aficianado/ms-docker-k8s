package com.db.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableFeignClients("com.db.ms")
//@EnableDiscoveryClient
public class UserStatsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserStatsServiceApplication.class, args);
	}

//	@Bean
//	public Sampler defaultSampler() {
//		return Sampler.ALWAYS_SAMPLE;
//	}
}
