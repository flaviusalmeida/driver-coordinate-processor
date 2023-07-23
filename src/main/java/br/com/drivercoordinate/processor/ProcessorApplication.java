package br.com.drivercoordinate.processor;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
public class ProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

}
