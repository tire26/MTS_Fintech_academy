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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private ConcurrentMap<String, CopyOnWriteArrayList<Animal>> animalsMap;
    private final CreateAnimalService createAnimalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void initAnimalsStorage() {
        animalsMap = new ConcurrentHashMap<>(createAnimalService.createUniqueAnimals());
    }

    @Override
    public ConcurrentMap<String, LocalDate> findLeapYearNames() {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }

        return animalsMap.values().parallelStream()
                .flatMap(List::stream)
                .filter(animal -> animal.getBirthDate().getYear() > 0 && isLeapYear(animal.getBirthDate().getYear()))
                .collect(Collectors.toConcurrentMap(
                        animal -> animal.getName() + " " + animal.getBreed() + " " + animal.getCost(),
                        Animal::getBirthDate
                ));
    }

    @Override
    public ConcurrentMap<Animal, Integer> findOlderAnimal(int N) {
        if (animalsMap == null) {
            throw new NullArgumentException("Список животных не может быть null");
        }
        if (N < 0) {
            throw new IllegalAgeException("Указан недопустимый возрастной порог N  < 0");
        }

        List<Map.Entry<Integer, Animal>> animalWrappers = animalsMap.values().parallelStream()
                .flatMap(List::stream)
                .filter(animal -> animal.getBirthDate().getYear() > 0)
                .map(animal -> Map.entry(calculateAge(animal.getBirthDate(), LocalDate.now()), animal))
                .collect(Collectors.toList());

        ConcurrentMap<Animal, Integer> result = animalWrappers.parallelStream()
                .filter(animal -> animal.getKey() > N)
                .collect(Collectors.toConcurrentMap(
                        Map.Entry::getValue,
                        Map.Entry::getKey
                ));

        if (result.isEmpty() && !animalWrappers.isEmpty()) {
            animalWrappers.parallelStream()
                    .max(Map.Entry.comparingByKey())
                    .ifPresent(it -> result.put(it.getValue(), it.getKey()));
        }
        return result;
    }


    @Override
    public ConcurrentMap<String, List<Animal>> findDuplicate() {
        if (animalsMap == null) {
            throw new IllegalArgumentException("Список животных не может быть null");
        }
        ConcurrentMap<String, List<Animal>> collect = animalsMap.values().parallelStream()
                .flatMap(List::stream)
                .collect(Collectors.groupingByConcurrent(Animal::toString));

        return collect.entrySet().parallelStream()
                .filter(entry -> entry.getValue().size() > 1)
                .peek(entry -> entry.getValue().remove(entry.getValue().size() - 1))
                .collect(Collectors.toConcurrentMap(
                        entry -> entry.getValue().get(0).getBreed(),
                        Map.Entry::getValue
                ));
    }

    @Override
    public void printDuplicate() {
        ConcurrentMap<String, List<Animal>> animalSet = findDuplicate();
        if (!animalSet.isEmpty()) {
            System.out.println("Повторяющиеся типы животных:");
            animalSet.forEach((s, i) -> System.out.println(s + "=" + i.size()));
        } else {
            System.out.println("Дубликатов животных не обнаружено.");
        }
    }

    @Override
    public Double findAverageAge(CopyOnWriteArrayList<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть пустым");
        }

        double averageYear = animals.parallelStream()
                .mapToLong(animal -> ChronoUnit.YEARS.between(animal.getBirthDate(), LocalDate.now()))
                .average()
                .orElseThrow(() -> new RuntimeException("Не удалось вычислить средний возраст"));
        System.out.println("Средний возраст животных: " + averageYear);
        return averageYear;
    }

    @Override
    public CopyOnWriteArrayList<Animal> findOldAndExpensive(CopyOnWriteArrayList<Animal> animals) {
        if (animals == null) {
            throw new IllegalArgumentException("Список животных не может быть пустым");
        }

        double averagePrice = animals.parallelStream()
                .mapToLong(value -> value.getCost().longValue())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Не удалось вычислить среднюю цену"));

        return animals.parallelStream()
                .filter(animal -> ChronoUnit.YEARS.between(animal.getBirthDate(), LocalDate.now()) > 5)
                .filter(animal -> animal.getCost().longValue() >= averagePrice)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    @Override
    public CopyOnWriteArrayList<String> findMinConstAnimals(CopyOnWriteArrayList<Animal> animals) throws Less3AnimalsException {
        if (animals == null) {
            throw new IllegalArgumentException("На вход подан null список");
        }
        if (animals.size() < 3) {
            throw new Less3AnimalsException("Список животных должен содержать хотя бы 3 элемента");
        }

        return animals.parallelStream()
                .sorted(Comparator.comparingLong(value -> value.getCost().longValue()))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .map(Animal::getName)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    public Map<String, CopyOnWriteArrayList<Animal>> getAnimalsMap() {
        return animalsMap;
    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, currentDate);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
