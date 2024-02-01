package ru.mts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mts.pet.Cat;
import ru.mts.pet.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@DisplayName("Тестиование метода equals класса AbstractAnimal")
public class AbstractAnimalEqualTest {

    private static AbstractAnimal cat1;
    private static AbstractAnimal cat2;
    private static AbstractAnimal cat3;
    private static AbstractAnimal dog;

    @BeforeAll
    static void prepareData() {
        cat1 = new Cat("Whiskers", BigDecimal.valueOf(2000), "quite", LocalDate.of(2015, 12, 28));
        cat2 = new Cat("Whiskers", BigDecimal.valueOf(2000), "quite", LocalDate.of(2015, 12, 28));
        cat3 = new Cat("Whiskers", BigDecimal.valueOf(2000), "quite", LocalDate.of(2015, 12, 28));

        dog = new Dog("Buddy", BigDecimal.valueOf(5000), "crazy", LocalDate.of(2014, 11, 22));
    }

    @Test
    @DisplayName("Проверка метода equals не равные объекты")
    void testNotEqualsAnimals() {
        assertNotEquals(cat1, dog);
    }

    @Test
    @DisplayName("Проверка метода equals на рефлексивность (x = x)")
    void testEqualsOverrideReflexivity() {
        assertEquals(cat1, cat1);
    }

    @Test
    @DisplayName("Проверка метода equals на симметричность (x = y) то (y = x)")
    void testEqualsOverrideSymmetry() {
        assertEquals(cat1.equals(cat2), cat2.equals(cat1));
    }

    @Test
    @DisplayName("Проверка метода equals на транзитвиность (x = y) и (y = z), то (x = z)")
    void testEqualsOverrideTransitivity() {
        assertEquals(cat1, cat2);
        assertEquals(cat2, cat3);
        assertEquals(cat1, cat3);
    }

    @Test
    @DisplayName("Проверка метода equals на согласованность")
    void testEqualsOverrideConsistency() {
        assertEquals(cat1.equals(cat2), cat1.equals(cat2));
    }

    @Test
    @DisplayName("Проверка метода equals на работу с null")
    void testEqualsOverrideNullCheck() {
        assertEquals(false, cat1.equals(null));
    }
}