package ru.mts;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.exception.Less3AnimalsException;
import ru.mts.model.Animal;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledTask {
    private final AnimalsRepository animalsRepository;

    public ScheduledTask(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedDelayString = "${app.fixedRate}")
    public void task() {
        animalsRepository.printDuplicate();
        System.out.println("-----------------------------------");

        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
        for (String s : leapYearNames.keySet()) {
            LocalDate localDate = leapYearNames.get(s);
            System.out.println("Животное с високосным годом рождения: " + s + " Дата: " + localDate);
            System.out.println("-----------------------------------");

        }
        int N = 15;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            Map<Animal, Integer> olderAnimal = animalsRepository.findOlderAnimal(N);
            for (Animal animal : olderAnimal.keySet()) {
                System.out.println("Животное с возрастом больше " + N + " лет:" + animal.getName() + "| дата рождения: " + animal.getBirthDate().format(formatter));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        AnimalsRepositoryImpl ar = (AnimalsRepositoryImpl) animalsRepository;
        List<Animal> animals = new ArrayList<>();
        for (List<Animal> value : ar.getAnimalsMap().values()) {
            animals.addAll(value);
        }
        try {
            animalsRepository.findMinConstAnimals(animals);
        } catch (Less3AnimalsException e) {
            System.out.println(e.getMessage());
        }
    }
}
