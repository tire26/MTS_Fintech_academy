package ru.mts.animalsconstructstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.animalsconstructstarter.factory.AnimalFactory;
import ru.mts.animalsconstructstarter.service.AnimalFactoryService;
import ru.mts.animalsconstructstarter.service.AnimalFactoryServiceImpl;


import java.util.Map;

@Configuration
public class AnimalFactoryServiceConfig {

    @Bean
    public AnimalFactoryService animalFactoryService(Map<String, AnimalFactory> animalFactoryMap) {
        return new AnimalFactoryServiceImpl(animalFactoryMap);
    }
}
