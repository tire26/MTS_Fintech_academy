package ru.mts.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;
import ru.mts.service.CreateAnimalService;

@TestConfiguration
public class AnimalsRepositoryTestConfig {

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}
