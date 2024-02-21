package ru.mts.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Primary;
import ru.mts.factory.AnimalFactory;
import ru.mts.factory.PetFactory;
import ru.mts.factory.PredatorFactory;
import ru.mts.model.AnimalType;
import ru.mts.service.AnimalFactoryService;
import ru.mts.service.AnimalFactoryServiceImpl;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

import java.util.List;

@TestConfiguration
public class AnimalFactoryTestConfig {
    @Bean
    @Primary
    public AnimalFactory petFactory() {
        return new PetFactory();
    }

    @Bean
    @Primary
    public AnimalFactory predatorFactory() {
        return new PredatorFactory();
    }

    @Bean
    @Primary
    public AnimalFactoryService animalFactoryService(List<AnimalFactory> factories) {
        return new AnimalFactoryServiceImpl(factories);
    }

    @Bean
    @Primary
    public CreateAnimalService createAnimalService(AnimalFactoryService animalFactoryService) {
        return new CreateAnimalServiceImpl(animalFactoryService, AnimalType.CAT);
    }
}
