package ru.mts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Profile("!test")
public class Main { 
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

