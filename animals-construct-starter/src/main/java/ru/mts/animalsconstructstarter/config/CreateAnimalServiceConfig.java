package ru.mts.animalsconstructstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.animalsconstructstarter.service.AnimalFactoryService;
import ru.mts.animalsconstructstarter.service.CreateAnimalService;
import ru.mts.animalsconstructstarter.service.CreateAnimalServiceImpl;


@Configuration
public class CreateAnimalServiceConfig {

    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService(AnimalFactoryService animalFactoryService) {
        return new CreateAnimalServiceImpl(animalFactoryService);
    }
}
