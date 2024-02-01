package ru.mts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.pet.Parrot;
import ru.mts.predator.Shark;
import ru.mts.predator.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование методов класса SearchServiceImpl")
public class SearchServiceImplTest {

    private static Animal[] leapYearTestAnimals;
    private static Animal[] duplicateAnimals;
    private static Animal[] olderAnimals;
    private static SearchService searchService;

    @BeforeAll
    static void prepareData() {
        searchService = new SearchServiceImpl();

        leapYearTestAnimals = new Animal[]{
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4)),
                new Shark("морти", new BigDecimal(21000), "ленивый", Collections.emptyList(), LocalDate.now().minusYears(13).minusDays(4)),
                new Shark("чипол", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(20).minusDays(4))
        };

        duplicateAnimals = new Animal[]{
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4)),
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4)),
                new Wolf("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4)),
                new Shark("морти", new BigDecimal(21000), "ленивый", Collections.emptyList(), LocalDate.now().minusYears(13).minusDays(4)),
                new Shark("чипол", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(20).minusDays(4))
        };

        olderAnimals = new Animal[]{
                new Parrot("Кеша", new BigDecimal(200), "говорящий", LocalDate.now().minusYears(19).minusDays(19)),
                new Shark("Кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(20).minusDays(4)),
                new Parrot("Попугайчик", new BigDecimal(150), "говорящий", LocalDate.now().minusYears(12).minusMonths(8).minusDays(17)),
                new Shark("Большая акула", new BigDecimal(30000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(29).minusMonths(5).minusDays(22)),
                new Parrot("Цветик", new BigDecimal(180), "говорящий", LocalDate.now().minusYears(9).minusMonths(5).minusDays(12)),
                new Shark("Маленькая акула", new BigDecimal(15000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(6).minusMonths(11).minusDays(27))
        };

    }

    @Test
    @DisplayName("тест правильности работы метода SearchServiceImpl.findLeapYearNames()")
    void testFindLeapYearNames() {
        String[] leapYearNames = searchService.findLeapYearNames(leapYearTestAnimals);
        String[] expected = new String[]{"чипол", "морти"};
        assertEquals(expected[0], leapYearNames[0]);
    }

    @Test
    @DisplayName("тест работы метода SearchServiceImpl.findLeapYearNames() с пустым массивом на вход")
    void testFindLeapYearNamesEmpty() {
        Animal[] arr = new Animal[]{};
        String[] result = searchService.findLeapYearNames(arr);
        assertEquals(0, result.length);
    }

    @Test
    @DisplayName("тест работы метода SearchServiceImpl.findLeapYearNames() с null на вход")
    void testFindLeapYearNamesNull() {
        Animal[] arr = null;
        assertThrows(IllegalArgumentException.class, () -> {
            searchService.findLeapYearNames(arr);
        });
    }

    @Test
    @DisplayName("тест работы метода SearchServiceImpl.findDuplicate() с null на вход")
    void testFindDuplicateNull() {
        Animal[] arr = null;
        assertThrows(IllegalArgumentException.class, () -> {
            searchService.findDuplicate(arr);
        });
    }

    @Test
    @DisplayName("тест работы метода SearchServiceImpl.findDuplicate() с пустым массивом на вход")
    void testFindDuplicatesEmpty() {
        Animal[] arr = new Animal[]{};
        Animal[] result = searchService.findDuplicate(arr);
        assertEquals(0, result.length);
    }

    @Test
    @DisplayName("тест правильности работы метода SearchServiceImpl.findDuplicate()")
    void testFindDuplicate() {
        Animal[] duplicate = searchService.findDuplicate(duplicateAnimals);
        Animal[] expected = new Animal[]{
                new Shark("кусь", new BigDecimal(20000), "кусается", Collections.emptyList(), LocalDate.now().minusYears(25).minusDays(4))
        };
        assertEquals(expected[0], duplicate[0]);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 7, 10})
    @DisplayName("тест правильности работы метода SearchServiceImpl.findOlderAnimal()")
    void testFindOlderAnimal(int n) {

        Animal[] olderAnimalsArray = searchService.findOlderAnimal(olderAnimals, n);
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
    @DisplayName("тест работы метода SearchServiceImpl.findOlderAnimal() с пустым массивом на вход")
    void testFindOlderAnimalEmpty() {
        Animal[] arr = new Animal[]{};
        Animal[] result = searchService.findOlderAnimal(arr, 10);
        assertEquals(0, result.length);
    }

    @Test
    @DisplayName("тест работы метода SearchServiceImpl.findOlderAnimal() с null массивом на вход")
    void testFindOlderAnimalNull() {
        Animal[] arr = null;
        assertThrows(IllegalArgumentException.class, () -> {
            searchService.findOlderAnimal(arr, 10);
        });
    }

    @Test
    @DisplayName("тест работы метода SearchServiceImpl.findOlderAnimal() с неправильным возрастом на вход")
    void testFindOlderAnimalWrongOld() {
        int n = -1;
        assertThrows(IllegalArgumentException.class, () -> {
            searchService.findOlderAnimal(olderAnimals, n);
        });
    }
}
