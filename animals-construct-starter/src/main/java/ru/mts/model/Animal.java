package ru.mts.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс описывает поведение всех животных
 */
public interface Animal {

    /**
     * @return возвращает породу питомца
     */
    String getBreed();

    /**
     * @return возвращает кличку животного
     */
    String getName();

    /**
     * @return возвращает цену животного в магазине
     */
    BigDecimal getCost();

    /**
     * @return возвращает описание характера животного
     */
    String getCharacter();


    /**
     * @return возвращает день рождения питомца
     */
    LocalDate getBirthDate();
}
