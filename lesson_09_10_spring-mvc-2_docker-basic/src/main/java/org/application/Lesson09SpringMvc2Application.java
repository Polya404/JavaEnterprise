package org.application;

import org.application.service.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Lesson09SpringMvc2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lesson09SpringMvc2Application.class, args);
		RestClient restClient = context.getBean(RestClient.class);
//		restClient.callMethod();
	}

}
