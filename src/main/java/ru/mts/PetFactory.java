package ru.mts;

import ru.mts.pet.Cat;
import ru.mts.pet.Dog;
import ru.mts.pet.Parrot;
import ru.mts.pet.Pet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class PetFactory implements AnimalFactory {

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

    /**
     * Метод создаёт уникального питомацы
     *
     * @return возвращает сгенерированного питомца
     */
    @Override
    public Animal createAnimal() {
        int randomAnimal = (int) (Math.random() * 3);
        int randomCharacter = (int) (Math.random() * ANIMAL_CHARACTERS.length);
        int randomName = (int) (Math.random() * NAMES.length);
        Pet pet;

        switch (randomAnimal) {
            case 0:
                pet = createCat(randomName, randomCharacter);
                break;
            case 1:
                pet = createDog(randomName, randomCharacter);
                break;
            case 2:
            default:
                pet = createParrot(randomName, randomCharacter);
                break;
        }
        return pet;
    }

    private Pet createCat(int randomName, int randomCharacter) {
        BigDecimal catCost = generateCost(30000, 5000);
        LocalDate minDate = LocalDate.of(2010, 1, 1);
        LocalDate maxDate = LocalDate.of(2020, 12, 31);
        LocalDate birthdate = generateBirthDate(minDate, maxDate);
        return new Cat(NAMES[randomName], catCost, ANIMAL_CHARACTERS[randomCharacter], birthdate);
    }

    private Pet createDog(int randomName, int randomCharacter) {
        BigDecimal dogCost = generateCost(50000, 5000);
        LocalDate minDate = LocalDate.of(2011, 1, 1);
        LocalDate maxDate = LocalDate.of(2020, 12, 31);
        LocalDate birthdate = generateBirthDate(minDate, maxDate);
        return new Dog(NAMES[randomName], dogCost, ANIMAL_CHARACTERS[randomCharacter], birthdate);
    }

    private Pet createParrot(int randomName, int randomCharacter) {
        BigDecimal parrotCost = generateCost(3000, 500);
        LocalDate minDate = LocalDate.of(2019, 1, 1);
        LocalDate maxDate = LocalDate.of(2020, 12, 31);
        LocalDate birthdate = generateBirthDate(minDate, maxDate);
        return new Parrot(NAMES[randomName], parrotCost, ANIMAL_CHARACTERS[randomCharacter], birthdate);
    }

    private BigDecimal generateCost(int max, int min) {
        BigDecimal bigDecimal = BigDecimal.valueOf(min + (Math.random() * max - min));
        return bigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

    private LocalDate generateBirthDate(LocalDate minDate, LocalDate maxDate) {
        if (minDate == null || maxDate == null || minDate.isAfter(maxDate)) {
            throw new IllegalArgumentException("Invalid date range");
        }
        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}
