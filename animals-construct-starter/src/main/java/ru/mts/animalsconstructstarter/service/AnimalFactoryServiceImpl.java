package ru.mts.animalsconstructstarter.service;

import ru.mts.animalsconstructstarter.factory.AnimalFactory;
import ru.mts.animalsconstructstarter.model.FactoryType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimalFactoryServiceImpl implements AnimalFactoryService {

    private final Map<FactoryType, AnimalFactory> factoryTypeAnimalFactoryMap;

    public AnimalFactoryServiceImpl(List<AnimalFactory> animalFactoryMap) {
        this.factoryTypeAnimalFactoryMap = animalFactoryMap.stream()
                .collect(Collectors.toMap(AnimalFactory::getFactoryType, entry -> entry));
    }

    @Override
    public AnimalFactory getFactoryBy(FactoryType factoryType) {
        if (factoryType != null) {
            return factoryTypeAnimalFactoryMap.get(factoryType);
        }
        throw new IllegalArgumentException();
    }
}
