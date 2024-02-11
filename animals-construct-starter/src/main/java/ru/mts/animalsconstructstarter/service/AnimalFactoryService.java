package ru.mts.animalsconstructstarter.service;

import ru.mts.animalsconstructstarter.factory.AnimalFactory;
import ru.mts.animalsconstructstarter.model.FactoryType;

public interface AnimalFactoryService {

    /**
     * ищет фабрику указанного типа животного
     *
     * @param factoryType тип фабрики
     * @return фабрику животных
     */
    AnimalFactory getFactoryBy(FactoryType factoryType);
}
