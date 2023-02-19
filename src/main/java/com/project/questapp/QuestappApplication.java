package com.project.questapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(/*exclude = { SecurityAutoConfiguration.class }*/)
public class QuestappApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestappApplication.class, args);
    }

}
