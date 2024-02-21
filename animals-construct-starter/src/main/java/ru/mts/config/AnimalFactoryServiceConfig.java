package ru.mts.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.mts.factory.AnimalFactory;
import ru.mts.factory.PetFactory;
import ru.mts.factory.PredatorFactory;
import ru.mts.service.AnimalFactoryService;
import ru.mts.service.AnimalFactoryServiceImpl;

import java.util.List;

@Configuration
public class AnimalFactoryServiceConfig {
    @Bean
    public AnimalFactory petFactory() {
        return new PetFactory();
    }

    @Bean
    public AnimalFactory predatorFactory() {
        return new PredatorFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public AnimalFactoryService animalFactoryService(List<AnimalFactory> factories) {
        return new AnimalFactoryServiceImpl(factories);
    }
}
