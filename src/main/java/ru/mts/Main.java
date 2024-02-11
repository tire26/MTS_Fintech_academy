package ru.mts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mts.animalsconstructstarter.model.Animal;
import ru.mts.repository.AnimalsRepository;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Main { 
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        AnimalsRepository animalsRepository = applicationContext.getBean(AnimalsRepository.class);
        animalsRepository.printDuplicate();
        System.out.println("-----------------------------------");

        String[] leapYearNames = animalsRepository.findLeapYearNames();
        for (String leapYearName : leapYearNames) {
            System.out.println("Животное с високосным годом рождения: " + leapYearName);
        }
        System.out.println("-----------------------------------");

        int N = 15;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Animal[] olderAnimal = animalsRepository.findOlderAnimal(N);
        for (Animal animal : olderAnimal) {
            System.out.println("Животное с возрастом больше " + N + " лет:" + animal.getName() + "| дата рождения: " + animal.getBirthDate().format(formatter));
        }
    }
}