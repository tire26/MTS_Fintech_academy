package ru.mts.animalsconstructstarter.service;

import ru.mts.animalsconstructstarter.factory.AnimalFactory;
import ru.mts.animalsconstructstarter.model.FactoryType;

import java.util.Map;
import java.util.stream.Collectors;

public class AnimalFactoryServiceImpl implements AnimalFactoryService {

    private final Map<FactoryType, AnimalFactory> factoryTypeAnimalFactoryMap;

    public AnimalFactoryServiceImpl(Map<String, AnimalFactory> animalFactoryMap) {
        this.factoryTypeAnimalFactoryMap = animalFactoryMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getValue().getFactoryType(), Map.Entry::getValue));
    }

    public AnimalFactory getFactoryBy(FactoryType factoryType) {
        if (factoryType != null) {
            return factoryTypeAnimalFactoryMap.get(factoryType);
        }
        throw new IllegalArgumentException();
    }
}
