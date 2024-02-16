package ru.mts.animalsconstructstarter.service;

import ru.mts.animalsconstructstarter.SetRandomAnimalType;
import ru.mts.animalsconstructstarter.factory.AnimalFactory;
import ru.mts.animalsconstructstarter.model.Animal;
import ru.mts.animalsconstructstarter.model.AnimalType;

public interface CreateAnimalService {

    /**
     * создаёт 10 уникальных животных и печатает их клички и черты
     */
    default Animal[] createUniqueAnimals() {
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
        return animals;
    }

    /**
     * Метод создаёт фабрику для создания животного
     *
     * @return фабрику по созданию животных
     */
    AnimalFactory getAnimalFactory();

    @SetRandomAnimalType
    void setAnimalType(AnimalType animalType);
}
