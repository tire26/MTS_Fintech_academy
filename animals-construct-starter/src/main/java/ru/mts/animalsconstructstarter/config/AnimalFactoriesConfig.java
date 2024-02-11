package ru.mts.animalsconstructstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.animalsconstructstarter.factory.PetFactory;
import ru.mts.animalsconstructstarter.factory.PredatorFactory;

@Configuration
public class AnimalFactoriesConfig {

    @Bean
    @ConditionalOnProperty(prefix = "animals.arrays.pet", name = { "names, characters"})
    public PetFactory petFactory() {
        return new PetFactory();
    }

    @Bean
    @ConditionalOnProperty(prefix = "animals.arrays.predator", name = { "names, characters"})
    public PredatorFactory predatorFactory() {
        return new PredatorFactory();
    }
}
