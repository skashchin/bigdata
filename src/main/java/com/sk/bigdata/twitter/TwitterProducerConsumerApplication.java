package com.sk.bigdata.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwitterProducerConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterProducerConsumerApplication.class, args);
	}
}