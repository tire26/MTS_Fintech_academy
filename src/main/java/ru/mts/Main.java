package ru.mts;

import ru.mts.pet.Parrot;
import ru.mts.predator.Shark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        SearchService searchService = new SearchServiceImpl();
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        Animal[] animals = createAnimalService.createUniqueAnimals();
        animals = Stream.concat(Arrays.stream(animals),
                Arrays.stream(createAnimalService.createUniqueAnimals(5))).toArray(Animal[]::new);

        List<Animal> duplicateAnimals = new ArrayList<>();
        duplicateAnimals.add(new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.of(2001, 12,
                28)));
        duplicateAnimals.add(new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.of(2001, 12, 28)));
        duplicateAnimals.add(new Parrot("кеша", new BigDecimal(200), "говорящий", LocalDate.now()));
        Animal[] duplicate = searchService.findDuplicate(duplicateAnimals.toArray(new Animal[0]));
        printDuplicates(duplicate);
        System.out.println("-----------------------------------");

        String[] leapYearNames = searchService.findLeapYearNames(animals);
        for (String leapYearName : leapYearNames) {
            System.out.println("Животное с високосным годом рождения: " + leapYearName);
        }
        System.out.println("-----------------------------------");

        int N = 15;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Animal[] olderAnimal = searchService.findOlderAnimal(animals, N);
        for (Animal animal : olderAnimal) {
            System.out.println("Животное с возрастом больше " + N + " лет:" + animal.getName() + "| дата рождения: " + animal.getBirthDate().format(formatter));
        }
    }

    private static void printDuplicates(Animal[] animals) {
        if (animals != null) {
            System.out.println("Повторяющиеся животные:");
            for (Animal duplicateAnimal : animals) {
                System.out.println(duplicateAnimal.toString());
            }
        } else {
            System.out.println("Дупликатов животных не обнаружено.");
        }
    }
}