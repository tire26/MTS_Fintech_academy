package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mts.model.Animal;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


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

        return animalsMap.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .filter(animal -> animal.getBirthDate().getYear() > 0 && isLeapYear(animal.getBirthDate().getYear()))
                        .map(animal -> new AbstractMap.SimpleEntry<>(entry.getKey() + " " + animal.getName() + " " + animal.getBreed() + " " + animal.getCost(), animal.getBirthDate())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }
        if (N < 0) {
            throw new IllegalArgumentException("Указан недопустимый возрастной порог (N).");
        }

        List<AnimalWrapper> animalWrappers = animalsMap.values().stream()
                .flatMap(Collection::stream)
                .filter(animal -> animal.getBirthDate().getYear() > 0)
                .map(animal -> new AnimalWrapper(calculateAge(animal.getBirthDate(), LocalDate.now()), animal))
                .collect(Collectors.toList());

        Map<Animal, Integer> result = animalWrappers.stream()
                .filter(animal -> animal.getAge() > N)
                .collect(Collectors.toMap(AnimalWrapper::getAnimal, AnimalWrapper::getAge));

        if (result.isEmpty() && !animalWrappers.isEmpty()) {
            result.put(animalWrappers.stream()
                            .max(Comparator.comparing(AnimalWrapper::getAge))
                            .get()
                            .getAnimal(),
                    animalWrappers.stream()
                            .max(Comparator.comparing(AnimalWrapper::getAge))
                            .get()
                            .getAge());
        }

        return result;
    }


    @Override
    public Map<String, List<Animal>> findDuplicate() {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }

        Map<String, List<Animal>> collect = animalsMap.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Animal::toString, Collectors.mapping(Function.identity(), Collectors.toList())))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .peek(entry -> entry.getValue().remove(entry.getValue().size() - 1))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return collect.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getValue().get(0).getBreed(), Map.Entry::getValue));
    }

    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> animalSet = findDuplicate();
        if (!animalSet.isEmpty()) {
            System.out.println("Повторяющиеся типы животных:");
            for (String s : animalSet.keySet()) {
                List<Animal> i = animalSet.get(s);
                System.out.println(s + "=" + i.size());
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

    class AnimalWrapper implements Animal {
        private Integer age;
        private Animal animal;

        public AnimalWrapper(Integer age, Animal animal) {
            this.age = age;
            this.animal = animal;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Animal getAnimal() {
            return animal;
        }

        public void setAnimal(Animal animal) {
            this.animal = animal;
        }

        @Override
        public String getBreed() {
            return animal.getBreed();
        }

        @Override
        public String getName() {
            return animal.getName();
        }

        @Override
        public BigDecimal getCost() {
            return animal.getCost();
        }

        @Override
        public String getCharacter() {
            return animal.getCharacter();
        }

        @Override
        public LocalDate getBirthDate() {
            return null;
        }
    }
}
