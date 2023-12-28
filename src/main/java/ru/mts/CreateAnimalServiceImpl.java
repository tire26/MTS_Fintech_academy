package ru.mts;

public class CreateAnimalServiceImpl implements CreateAnimalService {

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
                animalFactory = getAnimalFactory(i);
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
        AnimalFactory animalFactory;
        int random = (int) (Math.random() * 6);
        if (random > 10 / 2) {
            animalFactory = new PetFactory();
        } else {
            animalFactory = new PredatorFactory();
        }
        return animalFactory;
    }


    private AnimalFactory getAnimalFactory(int animalsCount) {
        AnimalFactory animalFactory;
        int random = (int) (Math.random() * animalsCount);
        if (random > animalsCount / 2) {
            animalFactory = new PetFactory();
        } else {
            animalFactory = new PredatorFactory();
        }
        return animalFactory;
    }
}
