package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mts.animalsconstructstarter.model.Animal;
import ru.mts.animalsconstructstarter.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private Animal[] animals;
    private final CreateAnimalService createAnimalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void initAnimalsStorage() {
        animals = createAnimalService.createUniqueAnimals();
    }

    @Override
    public String[] findLeapYearNames() {
        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }

        List<String> leapYearNames = new ArrayList<>();

        for (Animal animal : animals) {
            if (animal.getBirthDate().getYear() > 0 && isLeapYear(animal.getBirthDate().getYear())) {
                leapYearNames.add(animal.getName());
            }
        }

        return leapYearNames.toArray(new String[0]);
    }

    @Override
    public Animal[] findOlderAnimal(int N) {
        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }
        if (N < 0) {
            throw new IllegalArgumentException("Указан недопустимый возрастной порог (N).");
        }

        List<Animal> olderAnimals = new ArrayList<>();

        for (Animal animal : animals) {
            if (animal.getBirthDate().getYear() > 0 && calculateAge(animal.getBirthDate(), LocalDate.now()) > N) {
                olderAnimals.add(animal);
            }
        }

        return olderAnimals.toArray(new Animal[0]);
    }

    @Override
    public Set<Animal> findDuplicate() {
        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }
        Set<Animal> seenAnimals = new HashSet<>();
        Set<Animal> duplicateAnimals = new HashSet<>();

        for (Animal animal : animals) {
            if (!seenAnimals.add(animal)) {
                duplicateAnimals.add(animal);
            }
        }
        return duplicateAnimals;
    }

    @Override
    public void printDuplicate() {
        Set<Animal> animalSet = findDuplicate();
        if (!animalSet.isEmpty()) {
            System.out.println("Повторяющиеся животные:");
            for (Animal duplicateAnimal : animals) {
                System.out.println(duplicateAnimal.toString());
            }
        } else {
            System.out.println("Дупликатов животных не обнаружено.");
        }
    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, currentDate);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
