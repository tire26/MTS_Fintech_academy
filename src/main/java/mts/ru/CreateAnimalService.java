package mts.ru;

import mts.ru.pet.Pet;
import mts.ru.predator.Predator;

public interface CreateAnimalService {

    /**
     * создаёт 10 уникальных животных и печатает их клички и черты
     */
    default void createUniqueAnimals() {
        int i = 10;
        int random;
        Animal currAnimal;
        while (i > 0) {
            random = (int) (Math.random() * 6);
            if (random > i / 2) {
                currAnimal = generatePet();
            } else {
                currAnimal = generatePredator();
            }
            i--;
            System.out.println(currAnimal.getName() + " " + currAnimal.getCharacter() + "\n");
        }
    }

    Pet generatePet();

    Predator generatePredator();
}
