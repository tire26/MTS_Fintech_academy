package ru.mts;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchServiceImpl implements SearchService {

    @Override
    public String[] findLeapYearNames(Animal[] animals) {
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
    public Animal[] findOlderAnimal(Animal[] animals, int N) {
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
    public void findDuplicate(Animal[] animals) {
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

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, currentDate);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
