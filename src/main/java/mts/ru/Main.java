package mts.ru;

import mts.ru.pet.Parrot;
import mts.ru.pet.Pet;
import mts.ru.predator.Predator;
import mts.ru.predator.Shark;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CreateAnimalService createAnimalService = new CreateAnimalService() {
            @Override
            public Pet generatePet() {
                return new Parrot("кеша", new BigDecimal(200), "говорящий");
            }

            @Override
            public Predator generatePredator() {
                return new Shark("кусь", new BigDecimal(20000), "кусается", List.of());
            }
        };

        System.out.println("Генерация животных методом из CreateAnimalService");
        createAnimalService.createUniqueAnimals();

        createAnimalService = new CreateAnimalServiceImpl();
        System.out.println("Генерация животных методом из CreateAnimalServiceImpl");
        CreateAnimalServiceImpl createAnimalServiceImpl = (CreateAnimalServiceImpl) createAnimalService;
        createAnimalServiceImpl.createUniqueAnimals();

        System.out.println("Генерация n животных методом из CreateAnimalServiceImpl");
        createAnimalServiceImpl.createUniqueAnimals(2);
    }
}