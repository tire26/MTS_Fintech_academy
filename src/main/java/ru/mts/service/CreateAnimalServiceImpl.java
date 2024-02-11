package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.model.Animal;
import ru.mts.model.AnimalType;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    private AnimalType animalType;
    private final AnimalFactoryService animalFactoryService;

    public CreateAnimalServiceImpl(AnimalFactoryService animalFactoryService) {
        this.animalFactoryService = animalFactoryService;
    }

    /**
     * создаёт i животных и выводит их клички и породы
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
     * создаёт 10 уникальных животных
     *
     * @return выводит их клички и породы
     */
    @Override
    public Animal[] createUniqueAnimals() {
        int i = 10;
        Animal[] animals = new Animal[i];
        AnimalFactory animalFactory;
        Animal currAnimal;
        do {
            i--;
            animalFactory = getAnimalFactory();
            animalFactory.setAnimalType(animalType);
            currAnimal = animalFactory.createAnimal();
            animals[i] = currAnimal;
        } while (i > 0);
        return animals;
    }

    /**
     * создаёт животных и выводит их клички и породы
     *
     * @return фабрику по созданию животных
     */
    @Override
    public AnimalFactory getAnimalFactory() {
        return animalFactoryService.getFactoryBy(animalType.getFactoryType());
    }

    @Override
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
}
