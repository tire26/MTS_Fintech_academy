package ru.mts;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.config.AnimalFactoryTestConfig;
import ru.mts.exception.Less3AnimalsException;
import ru.mts.model.Animal;
import ru.mts.model.AnimalType;
import ru.mts.model.pet.*;
import ru.mts.model.predator.Shark;
import ru.mts.model.predator.Wolf;
import ru.mts.repository.AnimalsRepository;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = {AnimalFactoryTestConfig.class}
)
@ActiveProfiles("test")
@DisplayName("Тестирование класса AnimalsRepository")
public class AnimalsRepositoryTest {

    private AnimalsRepository animalsRepository;
    private static Field field;

    @PostConstruct
    public void init() throws NoSuchFieldException {
        Field field = animalsRepository.getClass().getDeclaredField("animalsMap");
        field.setAccessible(true);
        AnimalsRepositoryTest.field = field;
    }

    @AfterAll
    public static void afterAll() {
        if (field != null) {
            field.setAccessible(false);
        }
    }


    @Test
    @DisplayName("тест правильности работы метода AnimalsRepository.findLeapYearNames()")
    void testFindLeapYearNames() throws IllegalAccessException {
        Map<String, List<Animal>> animals = new HashMap<>();
        List<Animal> sharks = new ArrayList<>(List.of(
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.of(1999, 4, 10)),
                new Shark("морти", new BigDecimal(21000), "ленивый", Collections.emptyList(), LocalDate.of(2019, 2, 14)),
                new Shark("чипол", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.of(2004, 11, 4))
        ));
        animals.put(AnimalType.SHARK.name(), sharks);

        field.set(animalsRepository, animals);

        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
        String[] expected = new String[]{"чипол Акула 20000"};
        for (String s : leapYearNames.keySet()) {
            assertEquals(expected[0], s);
        }

    }

    @Test
    @DisplayName("тест работы метода AnimalsRepository.findLeapYearNames() с пустым массивом на вход")
    void testFindLeapYearNamesEmpty() throws IllegalAccessException {
        Map<String, List<Animal>> animals = new HashMap<>();
        field.set(animalsRepository, animals);

        Map<String, LocalDate> result = animalsRepository.findLeapYearNames();
        assertEquals(0, result.keySet().size());
    }

    @Test
    @DisplayName("тест работы метода AnimalsRepository.findLeapYearNames() с null на вход")
    void testFindLeapYearNamesNull() throws IllegalAccessException {
        field.set(animalsRepository, null);
        assertThrows(IllegalArgumentException.class, () -> animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("тест работы метода AnimalsRepository.findDuplicate() с null на вход")
    void testFindDuplicateNull() throws IllegalAccessException {
        field.set(animalsRepository, null);
        assertThrows(IllegalArgumentException.class, () -> animalsRepository.findDuplicate());
    }

    @Test
    @DisplayName("тест работы метода AnimalsRepository.findDuplicate() с пустым массивом на вход")
    void testFindDuplicatesEmpty() throws IllegalAccessException {
        Map<String, List<Animal>> animals = new HashMap<>();
        field.set(animalsRepository, animals);

        Map<String, List<Animal>> result = animalsRepository.findDuplicate();
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("тест правильности работы метода AnimalsRepository.findDuplicate()")
    void testFindDuplicate() throws IllegalAccessException {
        Map<String, List<Animal>> duplicateAnimals = new HashMap<>();
        List<Animal> sharks = new ArrayList<>(List.of(
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4)),
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4)),
                new Shark("морти", new BigDecimal(21000), "ленивый", Collections.emptyList(), LocalDate.now().minusYears(13).minusDays(4)),
                new Shark("чипол", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(20).minusDays(4))
        ));
        List<Animal> wolfs = new ArrayList<>(List.of(
                new Wolf("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4))
        ));
        duplicateAnimals.put(AnimalType.WOLF.name(), wolfs);
        duplicateAnimals.put(AnimalType.SHARK.name(), sharks);

        field.set(animalsRepository, duplicateAnimals);

        Map<String, List<Animal>> duplicate = animalsRepository.findDuplicate();
        String next = duplicate.keySet().iterator().next();
        Integer i = duplicate.get(next).size();
        assertEquals("Акула", next);
        assertEquals(1, i);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 7, 10, 100})
    @DisplayName("тест правильности работы метода AnimalsRepository.findOlderAnimal()")
    void testFindOlderAnimal(int n) throws IllegalAccessException {
        Map<String, List<Animal>> olderAnimals = new HashMap<>();
        Animal oldestAnimal = new Shark("Большая акула", new BigDecimal(30000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(29).minusMonths(5).minusDays(22));
        List<Animal> sharks = new ArrayList<>(List.of(
                new Shark("Кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(20).minusDays(4)),
                oldestAnimal,
                new Shark("Маленькая акула", new BigDecimal(15000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(6).minusMonths(11).minusDays(27))
        ));
        List<Animal> parrots = new ArrayList<>(List.of(
                new Parrot("Кеша", new BigDecimal(200), "говорящий", LocalDate.now().minusYears(19).minusDays(19)),
                new Parrot("Попугайчик", new BigDecimal(150), "говорящий", LocalDate.now().minusYears(12).minusMonths(8).minusDays(17)),
                new Parrot("Цветик", new BigDecimal(180), "говорящий", LocalDate.now().minusYears(9).minusMonths(5).minusDays(12))
        ));
        olderAnimals.put(AnimalType.PARROT.name(), parrots);
        olderAnimals.put(AnimalType.SHARK.name(), sharks);
        field.set(animalsRepository, olderAnimals);

        Map<Animal, Integer> olderAnimalsArray = animalsRepository.findOlderAnimal(n);
        switch (n) {
            case 5:
                assertEquals(6, olderAnimalsArray.keySet().size());
                break;
            case 7:
                assertEquals(5, olderAnimalsArray.keySet().size());
                break;
            case 10:
                assertEquals(4, olderAnimalsArray.keySet().size());
                break;
            case 100:
                Animal next = olderAnimalsArray.keySet().iterator().next();
                assertEquals(1, olderAnimalsArray.keySet().size());
                assertEquals(oldestAnimal, next);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + n);
        }
    }

    @Test
    @DisplayName("тест работы метода AnimalsRepository.findOlderAnimal() с пустым массивом на вход")
    void testFindOlderAnimalEmpty() throws IllegalAccessException {
        Map<String, List<Animal>> animals = new HashMap<>();
        field.set(animalsRepository, animals);

        Map<Animal, Integer> result = animalsRepository.findOlderAnimal(10);
        assertEquals(0, result.keySet().size());
    }

    @Test
    @DisplayName("тест работы метода AnimalsRepository.findOlderAnimal() с null массивом на вход")
    void testFindOlderAnimalNull() throws IllegalAccessException {
        field.set(animalsRepository, null);
        assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimal(10));
    }

    @Test
    @DisplayName("тест работы метода AnimalsRepository.findOlderAnimal() с неправильным возрастом на вход")
    void testFindOlderAnimalWrongOld() throws IllegalAccessException {
        int n = -1;
        Map<String, List<Animal>> animals = new HashMap<>();
        field.set(animalsRepository, animals);
        assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOlderAnimal(n));
    }

    @Test
    @DisplayName("тест работы метода findAverageAge с null массивом на вход")
    public void testFindAverageAge_NullList() {
        assertThrows(IllegalArgumentException.class, () -> animalsRepository.findAverageAge(null));
    }

    @Test
    @DisplayName("тест работы метода findAverageAge с массивом из 1 животного")
    public void testFindAverageAge_OneAnimal() {
        List<Animal> animals = List.of(new Dog("Dog", BigDecimal.valueOf(20000), "Кусается", LocalDate.now().minusYears(10)));
        Double averageAge = animalsRepository.findAverageAge(animals);
        assertEquals(10, averageAge);
    }

    @Test
    @DisplayName("тест работы метода findAverageAge с массивом животных")
    public void testFindAverageAge_MultipleAnimals() {
        List<Animal> animals = List.of(
                new Dog("Dog", BigDecimal.valueOf(20000), "Кусается", LocalDate.now().minusYears(3).minusMonths(1)),
                new Cat("Cat", BigDecimal.valueOf(20), "Ест много", LocalDate.now().minusYears(2).minusMonths(1).minusDays(2)),
                new Parrot("Bird", BigDecimal.valueOf(200), "Не умеет летать", LocalDate.now().minusMonths(10)),
                new Parrot("Bird 2", BigDecimal.valueOf(400), "Умеет летать, но не летает", LocalDate.now().minusYears(1).minusDays(10))
        );
        Double averageAge = animalsRepository.findAverageAge(animals);
        assertEquals(1.5, averageAge);
    }

    @Test
    @DisplayName("тест метода findOldAndExpensive с null массивом на вход")
    public void testFindOldAndExpensive_NullList() {
        assertThrows(IllegalArgumentException.class, () -> animalsRepository.findOldAndExpensive(null));
    }


    @Test
    @DisplayName("тест метода findOldAndExpensive с массивом животных не старше 5 лет")
    public void testFindOldAndExpensive_NoOldAndExpensiveAnimals() {
        List<Animal> animals = List.of(
                new Dog("Dog", BigDecimal.valueOf(100), "", LocalDate.now().minusYears(6).minusMonths(2).minusDays(5)),
                new Cat("Cat", BigDecimal.valueOf(200), "", LocalDate.now().minusYears(5).minusMonths(2).minusDays(5)),
                new Parrot("Bird", BigDecimal.valueOf(300), "", LocalDate.now().minusYears(4).minusDays(5).minusMonths(2))
        );
        List<Animal> result = animalsRepository.findOldAndExpensive(animals);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("тест метода findOldAndExpensive с массивом животных c 1 животным подходящем по критериям")
    public void testFindOldAndExpensive_OneOldAndExpensiveAnimal() {
        List<Animal> animals = List.of(
                new Dog("Dog", BigDecimal.valueOf(100), "", LocalDate.now().minusYears(6).minusMonths(2).minusDays(5)),
                new Cat("Cat", BigDecimal.valueOf(500), "", LocalDate.now().minusYears(8).minusMonths(2).minusDays(5)),
                new Parrot("Bird", BigDecimal.valueOf(300), "", LocalDate.now().minusYears(4).minusDays(5).minusMonths(2))
        );
        List<Animal> result = animalsRepository.findOldAndExpensive(animals);
        assertEquals(1, result.size());
        assertEquals("Cat", result.get(0).getName());
    }

    @Test
    @DisplayName("тест метода findOldAndExpensive с массивом животных подходящих по критериям")
    public void testFindOldAndExpensive_MultipleOldAndExpensiveAnimals() {
        Dog dog = new Dog("Dog", BigDecimal.valueOf(600), "", LocalDate.now().minusYears(8).minusMonths(4).minusDays(5));
        Cat cat = new Cat("Cat", BigDecimal.valueOf(500), "", LocalDate.now().minusYears(8).minusMonths(2).minusDays(5));
        List<Animal> animals = List.of(
                dog,
                cat,
                new Parrot("Bird", BigDecimal.valueOf(400), "", LocalDate.now().minusYears(8).minusDays(5).minusMonths(2)),
                new Shark("Shark", BigDecimal.valueOf(300), "", new ArrayList<>(), LocalDate.now().minusDays(20))
        );
        List<Animal> result = animalsRepository.findOldAndExpensive(animals);
        assertEquals(2, result.size());
        assertTrue(result.contains(dog));
        assertTrue(result.contains(cat));
    }

    @Test
    @DisplayName("тест метода findMinCostAnimals с null массивом")
    public void testFindMinCostAnimals_NullList() {
        List<Animal> animals = null;
        assertThrows(IllegalArgumentException.class, () -> animalsRepository.findMinConstAnimals(animals));
    }

    @Test
    @DisplayName("тест метода findMinCostAnimals с массивом из 2 животных")
    public void testFindMinCostAnimals_LessThanThreeElements() {
        List<Animal> animals = List.of(
                new Dog("Dog", BigDecimal.valueOf(100), "", LocalDate.now().minusYears(6).minusMonths(2).minusDays(5)),
                new Cat("Cat", BigDecimal.valueOf(500), "", LocalDate.now().minusYears(8).minusMonths(2).minusDays(5))
        );
        List<String> minCostAnimals;
        try {
            minCostAnimals = animalsRepository.findMinConstAnimals(animals);
            assertEquals(2, minCostAnimals.size());
        } catch (Less3AnimalsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("тест метода findMinCostAnimals с массивом из 3 животных")
    public void testFindMinCostAnimals_ExactlyThreeElements() {
        List<Animal> animals = List.of(
                new Dog("Dog", BigDecimal.valueOf(100), "", LocalDate.now().minusYears(6).minusMonths(2).minusDays(5)),
                new Cat("Cat", BigDecimal.valueOf(500), "", LocalDate.now().minusYears(8).minusMonths(2).minusDays(5)),
                new Parrot("Bird", BigDecimal.valueOf(300), "", LocalDate.now().minusYears(4).minusDays(5).minusMonths(2))
        );
        List<String> result;
        try {
            result = animalsRepository.findMinConstAnimals(animals);
            assertEquals(3, result.size());
            assertEquals("Dog", result.get(0));
            assertEquals("Cat", result.get(1));
            assertEquals("Bird", result.get(2));
        } catch (Less3AnimalsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("тест метода findMinCostAnimals с массивом из большего количества элементов")
    public void testFindMinCostAnimals_MoreThanThreeElements() {
        List<Animal> animals = List.of(
                new Dog("Dog", BigDecimal.valueOf(100), "", LocalDate.now().minusYears(6).minusMonths(2).minusDays(5)),
                new Cat("Cat", BigDecimal.valueOf(500), "", LocalDate.now().minusYears(8).minusMonths(2).minusDays(5)),
                new Parrot("Bird", BigDecimal.valueOf(300), "", LocalDate.now().minusYears(4).minusDays(5).minusMonths(2)),
                new Horse("Horse", BigDecimal.valueOf(400), "", LocalDate.now().minusYears(3).minusDays(5).minusMonths(2)),
                new Zebra("Zebra", BigDecimal.valueOf(200), "", LocalDate.now().minusYears(5).minusDays(5).minusMonths(2))
        );
        List<String> result;
        try {
            result = animalsRepository.findMinConstAnimals(animals);
            assertEquals(3, result.size());
            assertEquals("Zebra", result.get(0));
            assertEquals("Dog", result.get(1));
            assertEquals("Bird", result.get(2));
        } catch (Less3AnimalsException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    public void setAnimalsRepository(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }
}
