package com.github.irgalin.plannerbot;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class PlannerBotApplication {

    @PostConstruct
    public void init(){
        ApiContextInitializer.init();
    }

    public static void main(String[] args) {
        SpringApplication.run(PlannerBotApplication.class, args);
    }

}
