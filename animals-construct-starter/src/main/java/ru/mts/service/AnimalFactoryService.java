package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.model.AnimalType;

public interface AnimalFactoryService {

    /**
     * ищет фабрику указанного типа животного
     * @param animalType тип животного
     * @return фабрику животных
     */
    AnimalFactory getFactoryBy(AnimalType animalType);
}
