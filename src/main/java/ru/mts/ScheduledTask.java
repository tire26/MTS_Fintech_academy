package ru.mts;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.model.Animal;
import ru.mts.repository.AnimalsRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Animal[] olderAnimal = animalsRepository.findOlderAnimal(N);
        for (Animal animal : olderAnimal) {
            System.out.println("Животное с возрастом больше " + N + " лет:" + animal.getName() + "| дата рождения: " + animal.getBirthDate().format(formatter));
        }
    }
}
