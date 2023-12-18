package mts.ru;

import mts.ru.pet.Cat;
import mts.ru.pet.Dog;
import mts.ru.pet.Parrot;
import mts.ru.pet.Pet;
import mts.ru.predator.Crocodile;
import mts.ru.predator.Predator;
import mts.ru.predator.Shark;
import mts.ru.predator.Wolf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final static String[] ANIMAL_CHARACTERS = {
            "Ласковый", "игривый", "Спокойный", "Активный", "Умный", "Самостоятельный", "Жизнерадостный",
            "Терпеливый", "Любознательный", "Смешной",
            "Храбрый", "Сдержанный", "Лидер", "Послушный", "Бесшумный", "Спонтанный", "Нежный", "Заботливый", "Агрессивный", "Решительный",
            "Беспокойный", "Бдительный", "Непредсказуемый", "Ловкий", "Общительный", "Независимый", "Благородный", "Веселый", "Серьезный"
    };
    private final static String[] NAMES = {
            "Мурка", "Барсик", "Васька", "Матроскин", "Ляля",
            "Шарик", "Рекс", "Бобик", "Тузик", "Дружок",
            "Кеша", "Гоша", "Чиж", "Пушок", "Лорд"
    };

    private static BigDecimal generateCost(int max, int min) {
        BigDecimal bigDecimal = BigDecimal.valueOf(min + (Math.random() * max - min));
        return bigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * создаёт i животных и выводит их клички и породы
     * @param i - количество создаваемых животных
     */
    public void createUniqueAnimals(int i) {
        int random;
        Animal currAnimal;
        for (int j = 0; j < i; j++) {
            random = (int) (Math.random() * 6);
            if (random > i / 2) {
                currAnimal = generatePet();
            } else {
                currAnimal = generatePredator();
            }
            i--;
            System.out.println(currAnimal.getName() + " " + currAnimal.getBreed());
        }
    }

    /**
     * создаёт животных и выводит их клички и породы
     */
    @Override
    public void createUniqueAnimals() {
        int i = 10;
        int random;
        Animal currAnimal;
        do {
            random = (int) (Math.random() * 6);
            if (random > i / 2) {
                currAnimal = generatePet();
            } else {
                currAnimal = generatePredator();
            }
            i--;
            System.out.println(currAnimal.getName() + " " + currAnimal.getBreed());
        } while (i > 0);
    }

    @Override
    public Pet generatePet() {
        int randomAnimal = (int) (Math.random() * 3);
        int randomCharacter = (int) (Math.random() * ANIMAL_CHARACTERS.length);
        int randomName = (int) (Math.random() * NAMES.length);
        Pet pet;

        switch (randomAnimal) {
            case 0: {
                BigDecimal catCost = generateCost(30000, 5000);
                pet = new Cat(NAMES[randomName], catCost, ANIMAL_CHARACTERS[randomCharacter]);
                break;
            }
            case 1: {
                BigDecimal dogCost = generateCost(50000, 5000);
                pet = new Dog(NAMES[randomName], dogCost, ANIMAL_CHARACTERS[randomCharacter]);
                break;
            }
            case 2:
            default: {
                BigDecimal parrotCost = generateCost(3000, 500);
                pet = new Parrot(NAMES[randomName], parrotCost, ANIMAL_CHARACTERS[randomCharacter]);
            }

        }
        return pet;
    }

    @Override
    public Predator generatePredator() {
        int random = (int) (Math.random() * 3);
        int randomCharacter = (int) (Math.random() * ANIMAL_CHARACTERS.length);
        int randomName = (int) (Math.random() * NAMES.length);
        Predator predator;
        switch (random) {
            case 0: {
                BigDecimal crocodileCost = generateCost(3000000, 50000);
                predator = new Crocodile(NAMES[randomName],
                        crocodileCost,
                        ANIMAL_CHARACTERS[randomCharacter],
                        List.of(Cat.CAT_BREED, Dog.DOG_BREED));
                break;
            }
            case 1: {
                BigDecimal sharkCost = generateCost(30000000, 500000);
                predator = new Shark(NAMES[randomName], sharkCost, ANIMAL_CHARACTERS[randomCharacter], List.of());
                break;
            }
            case 2:
            default: {
                BigDecimal parrotCost = BigDecimal.valueOf((Math.random() * 300000));
                predator = new Wolf(NAMES[randomName],
                        parrotCost,
                        ANIMAL_CHARACTERS[randomCharacter],
                        List.of(Cat.CAT_BREED, Dog.DOG_BREED));
                break;
            }

        }
        return predator;
    }
}
