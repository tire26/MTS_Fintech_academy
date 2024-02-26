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
    public Map<Animal, Integer> findOlderAnimal(int N) {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }
        if (N < 0) {
            throw new IllegalArgumentException("Указан недопустимый возрастной порог (N).");
        }
        Animal oldestAnimal = null;
        int oldestAnimalAge = -1;
        Map<Animal, Integer> olderAnimalsMap = new HashMap<>();
        for (String s : animalsMap.keySet()) {
            List<Animal> animals = animalsMap.get(s);
            for (Animal animal : animals) {
                int animalAge = calculateAge(animal.getBirthDate(), LocalDate.now());
                if (animalAge > oldestAnimalAge) {
                    oldestAnimal = animal;
                    oldestAnimalAge = animalAge;
                }
                if (animal.getBirthDate().getYear() > 0 && animalAge > N) {
                    olderAnimalsMap.put(animal, animalAge);
                }
            }
        }
        if (olderAnimalsMap.keySet().isEmpty() && oldestAnimal != null) {
            olderAnimalsMap.put(oldestAnimal, oldestAnimalAge);
        }
        return olderAnimalsMap;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }
        Set<String> seenAnimals = new HashSet<>();
        Map<String, Integer> duplicateAnimals = new HashMap<>();

        for (String s : animalsMap.keySet()) {
            List<Animal> animals = animalsMap.get(s);
            for (Animal animal : animals) {
                if (!seenAnimals.add(animal.getBreed())) {
                    int i = duplicateAnimals.get(animal.getBreed()) == null ? 1 : duplicateAnimals.get(animal.getBreed());
                    duplicateAnimals.put(animal.getBreed(), ++i);
                }
            }
        }
        return duplicateAnimals;
    }

    @Override
    public void printDuplicate() {
        Map<String, Integer> animalSet = findDuplicate();
        if (!animalSet.isEmpty()) {
            System.out.println("Повторяющиеся типы животных:");
            for (String s : animalSet.keySet()) {
                Integer i = animalSet.get(s);
                System.out.println(s + "=" + i);
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
