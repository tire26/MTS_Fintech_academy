package ru.mts;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * интерфейс описывает поведение всех животных
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
     * @return возвразает цену животного в магизине
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
