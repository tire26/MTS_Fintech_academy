package ru.mts.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.model.AnimalType;
import ru.mts.service.AnimalFactoryService;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

@Configuration
public class CreateAnimalServiceConfig {

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public CreateAnimalService createAnimalService(AnimalFactoryService animalFactoryService) {
        return new CreateAnimalServiceImpl(animalFactoryService, AnimalType.CAT);
    }
}
