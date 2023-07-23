package br.com.drivercoordinate.processor;

import br.com.drivercoordinate.processor.repository.EventRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
public class ProcessorApplication {

	@Autowired
	EventRepository eventRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

}
