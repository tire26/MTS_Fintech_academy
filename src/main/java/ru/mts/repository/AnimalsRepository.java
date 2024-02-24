package ru.mts.repository;


import ru.mts.model.Animal;

import java.util.Set;

/**
 *  Хранилище создаваемых животных
 */
public interface AnimalsRepository {

    /**
     * Находит всех животных в хранилище которые родились в високосный год
     *
     * @return массив из имен животных
     */
    String[] findLeapYearNames();

    /**
     * Находит всех животных в хранилище, возраст которых
     * старше N лет
     * @param N возраст
     * @return массив животных
     */
    Animal[] findOlderAnimal(int N);

    /**
     * @return вовзращает массив животных в хранилище, у которых есть дупликаты
     */
    Set<Animal> findDuplicate();

    /**
     * Вызывает внутри метод findDuplicate() и выводит в консоль результат
     */
    void printDuplicate();
}
