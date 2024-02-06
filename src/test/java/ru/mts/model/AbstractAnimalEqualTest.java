package ru.mts.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mts.model.AbstractAnimal;
import ru.mts.model.pet.Cat;
import ru.mts.model.pet.Dog;

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
        cat1 = new Cat("Whiskers", BigDecimal.valueOf(2000), "quite", LocalDate.now().minusYears(8).minusMonths(2).minusDays(3));
        cat2 = new Cat("Whiskers", BigDecimal.valueOf(2000), "quite", LocalDate.now().minusYears(8).minusMonths(2).minusDays(3));
        cat3 = new Cat("Whiskers", BigDecimal.valueOf(2000), "quite", LocalDate.now().minusYears(8).minusMonths(2).minusDays(3));

        dog = new Dog("Buddy", BigDecimal.valueOf(5000), "crazy", LocalDate.now().minusYears(9).minusMonths(3).minusDays(10));
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