package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.model.Animal;
import ru.mts.model.AnimalType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    private AnimalType animalType;
    private final AnimalFactoryService animalFactoryService;

    public CreateAnimalServiceImpl(AnimalFactoryService animalFactoryService, AnimalType animalType) {
        this.animalFactoryService = animalFactoryService;
        if (animalType != null) {
            this.animalType = animalType;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Создаёт i животных и выводит их клички и породы
     *
     * @param i - количество создаваемых животных
     */
    public Animal[] createUniqueAnimals(int i) {
        if (i >= 0) {
            Animal[] animals = new Animal[i];
            Animal currAnimal;
            AnimalFactory animalFactory;
            for (int j = 0; j < i; j++) {
                animalFactory = getAnimalFactory();
                currAnimal = animalFactory.createAnimal();
                animals[j] = currAnimal;
            }
            return animals;
        } else {
            return new Animal[0];
        }
    }

    /**
     * Создаёт 10 уникальных животных
     *
     * @return выводит их клички и породы
     */
    @Override
    public Map<String, List<Animal>> createUniqueAnimals() {
        int i = 10;
        Animal[] animals = new Animal[i];
        AnimalFactory animalFactory;
        Animal currAnimal;
        do {
            i--;
            animalFactory = getAnimalFactory();
            currAnimal = animalFactory.createAnimal();
            animals[i] = currAnimal;
        } while (i > 0);
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        animalsMap.put(getAnimalType().name(), Arrays.asList(animals));
        return animalsMap;
    }

    /**
     * Создаёт животных и выводит их клички и породы
     *
     * @return фабрику по созданию животных
     */
    @Override
    public AnimalFactory getAnimalFactory() {
        return animalFactoryService.getFactoryBy(animalType);
    }

    @Override
    public void setAnimalType(AnimalType animalType) {
        if (animalType != null) {
            this.animalType = animalType;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public AnimalType getAnimalType() {
        return this.animalType;
    }
}
