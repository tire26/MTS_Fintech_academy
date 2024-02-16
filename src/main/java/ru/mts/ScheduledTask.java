package ru.mts;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.animalsconstructstarter.model.Animal;
import ru.mts.repository.AnimalsRepository;

import java.time.format.DateTimeFormatter;

@Component
@Profile("!test")
public class ScheduledTask {
    private final AnimalsRepository animalsRepository;

    public ScheduledTask(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedDelayString = "${app.fixedRate}")
    public void task() {
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
