package mts.ru;

import java.util.ArrayList;
import java.util.List;

public interface CreateAnimalService {

    /**
     * создаёт 10 уникальных животных и печатает их клички и черты
     */
    default List<Animal> createUniqueAnimals() {
        int i = 10;
        List<Animal> animals = new ArrayList<>(i);
        Animal currAnimal;
        AnimalFactory animalFactory;
        while (i > 0) {
            animalFactory = getAnimalFactory();
            currAnimal = animalFactory.createAnimal();
            i--;
            animals.add(currAnimal);
        }
        return animals;
    }

    /**
     * Метод создаёь фабрику для создания животного
     * @return фабрику по созданию животных
     */
    AnimalFactory getAnimalFactory();
}
