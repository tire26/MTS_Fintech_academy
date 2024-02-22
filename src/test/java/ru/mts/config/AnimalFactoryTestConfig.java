package ru.mts.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.model.AnimalType;
import ru.mts.service.AnimalFactoryService;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

@TestConfiguration
public class AnimalFactoryTestConfig {
    @Bean
    public CreateAnimalService createAnimalService(AnimalFactoryService animalFactoryService) {
        return new CreateAnimalServiceImpl(animalFactoryService, AnimalType.CAT);
    }
}
