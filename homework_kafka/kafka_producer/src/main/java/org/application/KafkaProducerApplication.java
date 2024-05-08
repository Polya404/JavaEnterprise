package org.application;

import org.application.service.LogisticService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaProducerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaProducerApplication.class, args);
        LogisticService logisticService = context.getBean(LogisticService.class);
        logisticService.sendParallelTracks();
    }
}
