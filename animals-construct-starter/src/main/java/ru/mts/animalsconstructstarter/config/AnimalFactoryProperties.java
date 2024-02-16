package ru.mts.animalsconstructstarter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:animals-factory-application.properties")
public class AnimalFactoryProperties {
}
