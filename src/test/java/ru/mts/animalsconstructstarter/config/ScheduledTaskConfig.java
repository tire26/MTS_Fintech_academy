package ru.mts.animalsconstructstarter.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.ScheduledTask;
import ru.mts.animalsconstructstarter.service.CreateAnimalService;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;

@TestConfiguration
public class ScheduledTaskConfig {

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }

    @Bean
    public ScheduledTask scheduledTask(AnimalsRepository animalsRepository) {
        return new ScheduledTask(animalsRepository);
    }
}
