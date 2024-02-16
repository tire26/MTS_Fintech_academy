package ru.mts.animalsconstructstarter.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mts.animalsconstructstarter.model.Animal;
import ru.mts.animalsconstructstarter.model.AnimalType;
import ru.mts.animalsconstructstarter.model.FactoryType;
import ru.mts.animalsconstructstarter.model.pet.Cat;
import ru.mts.animalsconstructstarter.model.pet.Dog;
import ru.mts.animalsconstructstarter.model.pet.Parrot;
import ru.mts.animalsconstructstarter.model.pet.Pet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class PetFactory implements AnimalFactory {

    @Value("${animals.arrays.pet.characters}")
    private String[] ANIMAL_CHARACTERS;

    @Value("${animals.arrays.pet.names}")
    private String[] NAMES;

    private final FactoryType factoryType = FactoryType.PET_FACTORY;

    private AnimalType animalType;

    /**
     * Метод создаёт уникального питомацы
     *
     * @return возвращает сгенерированного питомца
     */
    @Override
    public Animal createAnimal() {
        int randomCharacter = (int) (Math.random() * ANIMAL_CHARACTERS.length);
        int randomName = (int) (Math.random() * NAMES.length);
        Animal animal;
        switch (animalType) {
            case CAT:
                animal = createCat(randomName, randomCharacter);
                break;
            case DOG:
                animal = createDog(randomName, randomCharacter);
                break;
            case PARROT:
                animal = createParrot(randomName, randomCharacter);
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

    @Override
    public FactoryType getFactoryType() {
        return factoryType;
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
