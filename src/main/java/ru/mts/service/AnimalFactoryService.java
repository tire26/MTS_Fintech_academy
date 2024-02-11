package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.model.FactoryType;

public interface AnimalFactoryService {

    /**
     * ищет фабрику указанного типа животного
     *
     * @param factoryType тип фабрики
     * @return фабрику животных
     */
    AnimalFactory getFactoryBy(FactoryType factoryType);
}
