package com.dhbinh.yummybites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class YummybitesApplication {

    public static void main(String[] args) {
        SpringApplication.run(YummybitesApplication.class, args);
    }

}
