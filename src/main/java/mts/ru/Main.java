package mts.ru;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SearchService searchService = new SearchServiceImpl();
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        List<Animal> animals = createAnimalService.createUniqueAnimals();
        CreateAnimalServiceImpl createAnimalServiceImpl = (CreateAnimalServiceImpl) createAnimalService;
        animals.addAll(createAnimalServiceImpl.createUniqueAnimals(5));

        searchService.findDuplicate(animals);
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