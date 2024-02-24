package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mts.model.Animal;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private Map<String, List<Animal>> animalsMap;
    private final CreateAnimalService createAnimalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void initAnimalsStorage() {
        animalsMap = createAnimalService.createUniqueAnimals();
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }

        Map<String, LocalDate> leapYearNames = new HashMap<>();

        for (String s : animalsMap.keySet()) {
            List<Animal> animals = animalsMap.get(s);

            for (Animal animal : animals) {
                int year = animal.getBirthDate().getYear();
                if (year > 0 && isLeapYear(year)) {
                    leapYearNames.put(s + " " + animal.getName(), animal.getBirthDate());
                }
            }
        }
        return leapYearNames;
    }

    @Override
    public Animal[] findOlderAnimal(int N) {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }
        if (N < 0) {
            throw new IllegalArgumentException("Указан недопустимый возрастной порог (N).");
        }

        List<Animal> olderAnimals = new ArrayList<>();
        for (String s : animalsMap.keySet()) {
            List<Animal> animals = animalsMap.get(s);
            for (Animal animal : animals) {
                if (animal.getBirthDate().getYear() > 0 && calculateAge(animal.getBirthDate(), LocalDate.now()) > N) {
                    olderAnimals.add(animal);
                }
            }
        }
        return olderAnimals.toArray(new Animal[0]);
    }

    @Override
    public Set<Animal> findDuplicate() {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }
        Set<Animal> seenAnimals = new HashSet<>();
        Set<Animal> duplicateAnimals = new HashSet<>();

        for (String s : animalsMap.keySet()) {
            List<Animal> animals = animalsMap.get(s);
            for (Animal animal : animals) {
                if (!seenAnimals.add(animal)) {
                    duplicateAnimals.add(animal);
                }
            }
        }
        return duplicateAnimals;
    }

    @Override
    public void printDuplicate() {
        Set<Animal> animalSet = findDuplicate();
        if (!animalSet.isEmpty()) {
            System.out.println("Повторяющиеся животные:");
            for (String s : animalsMap.keySet()) {
                List<Animal> animals = animalsMap.get(s);
                for (Animal duplicateAnimal : animals) {
                    System.out.println(duplicateAnimal.toString());
                }
            }
        } else {
            System.out.println("Дубликатов животных не обнаружено.");
        }
    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, currentDate);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
