package ru.mts.animalsconstructstarter.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.animalsconstructstarter.factory.AnimalFactory;
import ru.mts.animalsconstructstarter.factory.PetFactory;
import ru.mts.animalsconstructstarter.factory.PredatorFactory;
import ru.mts.animalsconstructstarter.model.AnimalType;
import ru.mts.animalsconstructstarter.service.AnimalFactoryService;
import ru.mts.animalsconstructstarter.service.AnimalFactoryServiceImpl;
import ru.mts.animalsconstructstarter.service.CreateAnimalService;
import ru.mts.animalsconstructstarter.service.CreateAnimalServiceImpl;

import java.util.List;

@TestConfiguration
public class AnimalFactoryTestConfig {
    @Bean
    public AnimalFactory petFactory() {
        return new PetFactory();
    }

    @Bean
    public AnimalFactory predatorFactory() {
        return new PredatorFactory();
    }

    @Bean
    public AnimalFactoryService animalFactoryService(List<AnimalFactory> factories) {
        return new AnimalFactoryServiceImpl(factories);
    }

    @Bean
    public CreateAnimalService createAnimalService(AnimalFactoryService animalFactoryService) {
        return new CreateAnimalServiceImpl(animalFactoryService, AnimalType.CAT);
    }
}
