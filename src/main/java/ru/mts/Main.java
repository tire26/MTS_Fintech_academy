package ru.mts;

import ru.mts.pet.Parrot;
import ru.mts.predator.Shark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SearchService searchService = new SearchServiceImpl();
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        List<Animal> animals = createAnimalService.createUniqueAnimals();
        CreateAnimalServiceImpl createAnimalServiceImpl = (CreateAnimalServiceImpl) createAnimalService;
        animals.addAll(createAnimalServiceImpl.createUniqueAnimals(5));

        List<Animal> duplicateAnimals = new ArrayList<>();
        duplicateAnimals.add(new Shark("кусь", new BigDecimal(20000), "кусается", List.of(), LocalDate.of(2001, 12, 28)));
        duplicateAnimals.add(new Shark("кусь", new BigDecimal(20000), "кусается", List.of(), LocalDate.of(2001, 12, 28)));
        duplicateAnimals.add(new Parrot("кеша", new BigDecimal(200), "говорящий", LocalDate.now()));
        searchService.findDuplicate(duplicateAnimals);
        System.out.println("-----------------------------------");

        List<String> leapYearNames = searchService.findLeapYearNames(animals);
        for (String leapYearName : leapYearNames) {
            System.out.println("Животное с високосным годом рождения: " + leapYearName);
        }
        System.out.println("-----------------------------------");

        int N = 10;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Animal> olderAnimal = searchService.findOlderAnimal(animals, N);
        for (Animal animal : olderAnimal) {
            System.out.println("Животное с возрастом больше " + N + " лет:" + animal.getName() + "| дата рождения: " + animal.getBirthDate().format(formatter));
        }
    }
}