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
import ru.mts.model.Animal;
import ru.mts.model.AnimalType;
import ru.mts.model.pet.Parrot;
import ru.mts.model.predator.Shark;
import ru.mts.model.predator.Wolf;
import ru.mts.repository.AnimalsRepository;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4)),
                new Shark("морти", new BigDecimal(21000), "ленивый", Collections.emptyList(), LocalDate.now().minusYears(13).minusDays(4)),
                new Shark("чипол", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(20).minusDays(4))
        ));
        animals.put(AnimalType.SHARK.name(), sharks);

        field.set(animalsRepository, animals);

        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
        String[] expected = new String[]{"SHARK чипол"};
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

        Set<Animal> result = animalsRepository.findDuplicate();
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

        Set<Animal> duplicate = animalsRepository.findDuplicate();
        Animal[] expected = new Animal[]{
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4))
        };
        Animal next = duplicate.iterator().next();
        assertEquals(expected[0], next);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 7, 10})
    @DisplayName("тест правильности работы метода AnimalsRepository.findOlderAnimal()")
    void testFindOlderAnimal(int n) throws IllegalAccessException {
        Map<String, List<Animal>> olderAnimals = new HashMap<>();
        List<Animal> sharks = new ArrayList<>(List.of(
                new Shark("Кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(20).minusDays(4)),
                new Shark("Большая акула", new BigDecimal(30000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(29).minusMonths(5).minusDays(22)),
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

        Animal[] olderAnimalsArray = animalsRepository.findOlderAnimal(n);
        switch (n) {
            case 5:
                assertEquals(6, olderAnimalsArray.length);
                break;
            case 7:
                assertEquals(5, olderAnimalsArray.length);
                break;
            case 10:
                assertEquals(4, olderAnimalsArray.length);
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

        Animal[] result = animalsRepository.findOlderAnimal(10);
        assertEquals(0, result.length);
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


    @Autowired
    public void setAnimalsRepository(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }
}
