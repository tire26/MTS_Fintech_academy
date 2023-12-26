package ru.mts;

import java.util.*;

public class SearchServiceImpl implements SearchService {

    private final int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    @Override
    public List<String> findLeapYearNames(List<Animal> animals) {
        List<String> leapYearNames = new ArrayList<>();

        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }

        for (Animal animal : animals) {
            if (animal.getBirthDate().getYear() > 0 && isLeapYear(animal.getBirthDate().getYear())) {
                leapYearNames.add(animal.getName());
            }
        }

        return leapYearNames;
    }

    @Override
    public List<Animal> findOlderAnimal(List<Animal> animals, int N) {
        List<Animal> olderAnimals = new ArrayList<>();

        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }

        if (N >= 0) {
            for (Animal animal : animals) {
                if (animal.getBirthDate().getYear() > 0 && calculateAge(animal.getBirthDate().getYear()) > N) {
                    olderAnimals.add(animal);
                }
            }
        } else {
            System.out.println("Указан недопустимый возрастной порог (N).");
        }

        return olderAnimals;
    }

    @Override
    public void findDuplicate(List<Animal> animals) {
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

        if (!duplicateAnimals.isEmpty()) {
            System.out.println("Повторяющиеся животные:");
            for (Animal duplicateAnimal : duplicateAnimals) {
                System.out.println(duplicateAnimal.toString());
            }
        } else {
            System.out.println("Дупликатов животных не обнаружено.");
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private int calculateAge(int birthYear) {
        return currentYear - birthYear;
    }
}
