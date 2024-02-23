package ru.mts.service;

import org.springframework.stereotype.Service;
import ru.mts.factory.AnimalFactory;
import ru.mts.model.AnimalType;
import ru.mts.model.FactoryType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnimalFactoryServiceImpl implements AnimalFactoryService {

    private final Map<FactoryType, AnimalFactory> factoryTypeAnimalFactoryMap;

    public AnimalFactoryServiceImpl(List<AnimalFactory> animalFactoryMap) {
        this.factoryTypeAnimalFactoryMap = animalFactoryMap.stream()
                .collect(Collectors.toMap(AnimalFactory::getFactoryType, entry -> entry));
    }

    @Override
    public AnimalFactory getFactoryBy(AnimalType animalType) {
        if (animalType != null) {
            AnimalFactory animalFactory = factoryTypeAnimalFactoryMap.get(animalType.getFactoryType());
            animalFactory.setAnimalType(animalType);
            return animalFactory;
        }
        throw new IllegalArgumentException();
    }
}
