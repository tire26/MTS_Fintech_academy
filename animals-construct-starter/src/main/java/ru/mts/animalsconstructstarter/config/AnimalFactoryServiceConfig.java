package ru.mts.animalsconstructstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.mts.animalsconstructstarter.factory.AnimalFactory;
import ru.mts.animalsconstructstarter.factory.PetFactory;
import ru.mts.animalsconstructstarter.factory.PredatorFactory;
import ru.mts.animalsconstructstarter.service.AnimalFactoryService;
import ru.mts.animalsconstructstarter.service.AnimalFactoryServiceImpl;

import java.util.List;

@Configuration
@Profile("!test")
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
