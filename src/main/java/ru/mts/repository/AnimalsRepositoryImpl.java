package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mts.exception.IllegalAgeException;
import ru.mts.exception.Less3AnimalsException;
import ru.mts.exception.NullArgumentException;
import ru.mts.model.Animal;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
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

        return animalsMap.values().stream()
                .flatMap(Collection::stream)
                .filter(animal -> animal.getBirthDate().getYear() > 0 && isLeapYear(animal.getBirthDate().getYear()))
                .map(animal -> new AbstractMap.SimpleEntry<>(animal.getName() + " " + animal.getBreed() + " " + animal.getCost(), animal.getBirthDate()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) {
        if (animalsMap == null) {
            throw new NullArgumentException("Список животных не может быть null");
        }
        if (N < 0) {
            throw new IllegalAgeException("Указан недопустимый возрастной порог N  < 0");
        }

        List<Map.Entry<Integer, Animal>> animalWrappers = animalsMap.values().stream()
                .flatMap(Collection::stream)
                .filter(animal -> animal.getBirthDate().getYear() > 0)
                .map(animal -> Map.entry(calculateAge(animal.getBirthDate(), LocalDate.now()), animal))
                .collect(Collectors.toList());

        Map<Animal, Integer> result = animalWrappers.stream()
                .filter(animal -> animal.getKey() > N)
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        if (result.isEmpty() && !animalWrappers.isEmpty()) {
            animalWrappers.stream()
                    .max(Map.Entry.comparingByKey())
                    .ifPresent(it -> result.put(it.getValue(), it.getKey()));
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

    @Override
    public Double findAverageAge(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть пустым");
        }

        double averageYear = animals.stream()
                .mapToLong(animal -> ChronoUnit.YEARS.between(animal.getBirthDate(), LocalDate.now()))
                .average()
                .orElseThrow(() -> new RuntimeException("Не удалось вычислить средний возраст"));
        System.out.println("Средний возраст животных: " + averageYear);
        return averageYear;
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть пустым");
        }

        double averagePrice = animals.stream()
                .mapToLong(value -> value.getCost().longValue())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Не удалось вычислить среднюю цену"));

        return animals.stream()
                .filter(animal -> ChronoUnit.YEARS.between(animal.getBirthDate(), LocalDate.now()) > 5)
                .filter(animal -> animal.getCost().longValue() >= averagePrice)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
    }


    @Override
    public List<String> findMinConstAnimals(List<Animal> animals) throws Less3AnimalsException {
        if (animals == null) {
            throw new IllegalArgumentException("На вход подан null список");
        }
        if (animals.size() < 3) {
            throw new Less3AnimalsException("Список животных должен содержать хотя бы 3 элемента");
        }

        return animals.stream()
                .sorted(Comparator.comparingLong(value -> value.getCost().longValue()))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .map(Animal::getName)
                .collect(Collectors.toList());
    }

    public Map<String, List<Animal>> getAnimalsMap() {
        return animalsMap;
    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, currentDate);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
