package ru.mts.animalsconstructstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:animals-factory-application.properties", encoding = "UTF-8")
public class AnimalFactoryProperties {
}
