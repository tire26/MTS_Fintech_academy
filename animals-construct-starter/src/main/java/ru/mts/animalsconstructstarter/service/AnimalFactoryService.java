package ru.mts.animalsconstructstarter.service;

import ru.mts.animalsconstructstarter.factory.AnimalFactory;
import ru.mts.animalsconstructstarter.model.AnimalType;
import ru.mts.animalsconstructstarter.model.FactoryType;

public interface AnimalFactoryService {

    /**
     * ищет фабрику указанного типа животного
     * @param animalType тип животного
     * @return фабрику животных
     */
    AnimalFactory getFactoryBy(AnimalType animalType);
}
