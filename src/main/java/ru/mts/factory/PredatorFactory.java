package ru.mts.factory;

import ru.mts.model.Animal;
import ru.mts.model.AnimalType;
import ru.mts.model.pet.Cat;
import ru.mts.model.pet.Dog;
import ru.mts.model.predator.Crocodile;
import ru.mts.model.predator.Predator;
import ru.mts.model.predator.Shark;
import ru.mts.model.predator.Wolf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class PredatorFactory implements AnimalFactory {

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
    private AnimalType animalType;

    /**
     * метод создаёт уникальное животное хищник
     *
     * @return животное
     */
    @Override
    public Animal createAnimal() {
        int randomCharacter = (int) (Math.random() * ANIMAL_CHARACTERS.length);
        int randomName = (int) (Math.random() * NAMES.length);
        Animal animal;
        switch (animalType) {
            case CROCODILE:
                animal = createCrocodile(randomName, randomCharacter);
                break;
            case SHARK:
                animal = createShark(randomName, randomCharacter);
                break;
            case WOLF:
                animal = createWolf(randomName, randomCharacter);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + animalType);
        }
        return animal;
    }

    @Override
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    private Predator createCrocodile(int randomName, int randomCharacter) {
        BigDecimal crocodileCost = generateCost(3000000, 50000);
        LocalDate minDate = LocalDate.of(1980, 1, 1);
        LocalDate maxDate = LocalDate.of(2020, 12, 31);
        LocalDate birthdate = generateBirthDate(minDate, maxDate);
        return new Crocodile(NAMES[randomName], crocodileCost, ANIMAL_CHARACTERS[randomCharacter], Arrays.asList(Cat.CAT_BREED, Dog.DOG_BREED), birthdate);
    }

    private Predator createShark(int randomName, int randomCharacter) {
        BigDecimal sharkCost = generateCost(30000000, 500000);
        LocalDate minDate = LocalDate.of(1990, 1, 1);
        LocalDate maxDate = LocalDate.of(2020, 12, 31);
        LocalDate birthdate = generateBirthDate(minDate, maxDate);
        return new Shark(NAMES[randomName], sharkCost, ANIMAL_CHARACTERS[randomCharacter], Collections.emptyList(), birthdate);

    }

    private Predator createWolf(int randomName, int randomCharacter) {
        BigDecimal wolfCost = BigDecimal.valueOf((Math.random() * 300000));
        LocalDate minDate = LocalDate.of(2008, 1, 1);
        LocalDate maxDate = LocalDate.of(2020, 12, 31);
        LocalDate birthdate = generateBirthDate(minDate, maxDate);
        return new Wolf(NAMES[randomName], wolfCost, ANIMAL_CHARACTERS[randomCharacter], Arrays.asList(Cat.CAT_BREED, Dog.DOG_BREED), birthdate);
    }

    private BigDecimal generateCost(int max, int min) {
        BigDecimal bigDecimal = BigDecimal.valueOf(min + (Math.random() * max - min));
        return bigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

    private LocalDate generateBirthDate(LocalDate minDate, LocalDate maxDate) {
        if (minDate == null || maxDate == null || minDate.isAfter(maxDate)) {
            throw new IllegalArgumentException("Неправильный интервал дат");
        }
        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}
