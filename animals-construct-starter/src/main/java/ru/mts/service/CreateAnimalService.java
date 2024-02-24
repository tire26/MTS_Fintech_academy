package ru.mts.service;

import ru.mts.SetRandomAnimalType;
import ru.mts.factory.AnimalFactory;
import ru.mts.model.Animal;
import ru.mts.model.AnimalType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CreateAnimalService {

    /**
     * Создаёт 10 уникальных животных и печатает их клички и черты
     */
    default Map<String, List<Animal>> createUniqueAnimals() {
        int i = 10;
        Animal[] animals = new Animal[i];
        Animal currAnimal;
        AnimalFactory animalFactory;
        while (i > 0) {
            animalFactory = getAnimalFactory();
            currAnimal = animalFactory.createAnimal();
            i--;
            animals[i] = currAnimal;
        }
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        animalsMap.put(getAnimalType().name(), Arrays.asList(animals));
        return animalsMap;
    }

    /**
     * Метод создаёт фабрику для создания животного
     *
     * @return фабрику по созданию животных
     */
    AnimalFactory getAnimalFactory();

    @SetRandomAnimalType
    void setAnimalType(AnimalType animalType);

    AnimalType getAnimalType();
}
