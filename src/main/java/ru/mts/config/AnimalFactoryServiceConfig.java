package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.factory.AnimalFactory;
import ru.mts.service.AnimalFactoryService;
import ru.mts.service.AnimalFactoryServiceImpl;

import java.util.Map;

@Configuration
public class AnimalFactoryServiceConfig {

    @Bean
    public AnimalFactoryService animalFactoryService(Map<String, AnimalFactory> animalFactoryMap) {
        return new AnimalFactoryServiceImpl(animalFactoryMap);
    }
}
