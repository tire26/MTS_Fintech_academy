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
            System.out.println(currAnimal.toString());
        }
    }

    /**
     * создаёт одного уникального домашнего животного
     *
     * @return возвращает питомца
     */
    Pet generatePet();

    /**
     * создаёт одного уникального хищника
     *
     * @return возвращает хищника
     */
    Predator generatePredator();
}
